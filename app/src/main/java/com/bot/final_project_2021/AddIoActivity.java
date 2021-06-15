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

import java.util.Calendar;

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
        int mYear, mMonth, mDay;

        /** 抓取目前手機時間 */
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        String date = Integer.toString(mYear) + "/";    // (YYYY/ )
        if (mMonth < 10) { //(YYYY/0 )
            date += "0";
        }
        date += Integer.toString(mMonth) + "/"; //(YYYY/MM )
        if (mDay < 10) {  //(YYYY/MM/0 )
            date += "0";
        }
        date += Integer.toString(mDay);   //(YYYY/MM/DD)
        mDateView.setText(date);

        final Button button = findViewById(R.id.button_add_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] dateTemp = getDate(mDateView.getText().toString());
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mDateView.getText()) || TextUtils.isEmpty(mMoneyView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else{
                    replyIntent.putExtra(EXTRA_REPLY, "A");
                    replyIntent.putExtra(getString(R.string.extra_year), dateTemp[0]);
                    replyIntent.putExtra(getString(R.string.extra_month), dateTemp[1]);
                    replyIntent.putExtra(getString(R.string.extra_day), dateTemp[2]);
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

    private int[] getDate(String date){
        int[] x = {0, 0, 0};
        int i;
        String[] temp;
        temp = date.split("[/]");
        for(i=0; i < 3; i++) {
            x[i] = Integer.parseInt(temp[i]);
        }
        return x;
    }
}