package com.bot.final_project_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toAddData(View view){
        Intent intent = new Intent(this, AddIoActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "It can go to add data.", Toast.LENGTH_SHORT).show();
    }
    public void toHistory(View view){
        Toast.makeText(this, "It can go to History.", Toast.LENGTH_SHORT).show();
    }
    public void toSettings(View view){
        Toast.makeText(this, "It can go to Settings.", Toast.LENGTH_SHORT).show();
    }
}