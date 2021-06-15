package com.bot.final_project_2021;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bot.final_project_2021.Database.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_MONEY_ACTIVITY_REQUEST_CODE = 1;
    private MoneyViewModel mMoneyViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.main_title);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setTitleTextColor(getColor(R.color.white));

        toolbar.showContextMenu();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MoneyListAdapter adapter = new MoneyListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMoneyViewModel = ViewModelProviders.of(this).get(MoneyViewModel.class);

        mMoneyViewModel.getAllMoneys().observe(this, new Observer<List<Money>>(){
            @Override
            public void onChanged(@NonNull final List<Money> moneys){
                adapter.setMoneys(moneys);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddIoActivity.class);
                startActivityForResult(intent, NEW_MONEY_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getGroupId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_MONEY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            int mYear = data.getIntExtra(getString(R.string.extra_year), 2021);
            int mMonth = data.getIntExtra(getString(R.string.extra_month), 6);
            int mDay = data.getIntExtra(getString(R.string.extra_day), 4);
            String mTag = data.getStringExtra(getString(R.string.extra_tag));
            int mMoney = data.getIntExtra(getString(R.string.extra_money), 120);
            Boolean mType = data.getBooleanExtra(getString(R.string.extra_type), false);
            String mText = data.getStringExtra(getString(R.string.extra_text));
            Money money = new Money(mYear, mMonth, mDay, mTag, mMoney, mType, mText);
            mMoneyViewModel.insert(money);
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }

//    public void toAddData(View view){
//        Intent intent = new Intent(this, AddIoActivity.class);
//        startActivity(intent);
//        //Toast.makeText(this, "It can go to add data.", Toast.LENGTH_SHORT).show();
//    }
    public Boolean toDetail(MenuItem item){
        Toast.makeText(this, "It can go to look Detail.", Toast.LENGTH_SHORT).show();
        return false;
    }
    public Boolean toSettings(MenuItem item){
        Toast.makeText(this, "It can go to Settings.", Toast.LENGTH_SHORT).show();
        return false;
    }
}