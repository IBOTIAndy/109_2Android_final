package com.bot.final_project_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddIoActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roommoneyssample.REPLY";

    private EditText mDateView;
    private EditText mTagView;
    private EditText mMoneyView;
    private RadioGroup mTypeGroup;
    private EditText mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_io);
        mDateView = findViewById(R.id.editTextDate);
        mTagView = findViewById(R.id.editTextTag);
        mMoneyView = findViewById(R.id.editTextMoney);
        mTypeGroup = findViewById(R.id.moneyTypeGroup);
        mTextView = findViewById(R.id.editTextText);

        final Button button = findViewById(R.id.button_add_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mDateView.getText()) || TextUtils.isEmpty(mMoneyView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else{
                    replyIntent.putExtra(getString(R.string.extra_date), mDateView.getText().toString());
                    replyIntent.putExtra(getString(R.string.extra_tag), mTagView.getText().toString());
                    replyIntent.putExtra(getString(R.string.extra_money), Integer.parseInt(mMoneyView.getText().toString()));
                    replyIntent.putExtra(getString(R.string.extra_type), getMoneyType());
                    replyIntent.putExtra(getString(R.string.extra_text), mTextView.getText().toString());
                    setResult(RESULT_OK, replyIntent);
                }
                finish();   //跳離add_I/O_Activity
            }
        });
    }

    private Boolean getMoneyType(){
        if(mTypeGroup.getCheckedRadioButtonId() == R.id.radioIncome){
            return true;
        }
        else if(mTypeGroup.getCheckedRadioButtonId() == R.id.radioOutcome) {
            return false;
        }
        Log.e("Add I/O activity", "function: \'getMoneyType\' Error");
        return false;
    }
}