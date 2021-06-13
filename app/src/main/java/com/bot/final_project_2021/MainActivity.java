package com.bot.final_project_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bot.final_project_2021.RoomDatabase.MyData;

public class MainActivity extends AppCompatActivity {
    MyData nowSelectData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toAddData(View view){
        Toast.makeText(this, "It can go to add data.", Toast.LENGTH_SHORT).show();
    }

    public void toHistory(View view){
        Intent intent = new Intent(this, moneyLayout.class);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            Log.d("Intent", "Can't go to history.");
        }
//        Toast.makeText(this, "It can go to History.", Toast.LENGTH_SHORT).show();
    }

    public void toSettings(View view){
        Toast.makeText(this, "It can go to Settings.", Toast.LENGTH_SHORT).show();
    }
}