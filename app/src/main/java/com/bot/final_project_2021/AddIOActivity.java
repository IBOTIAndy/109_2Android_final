package com.bot.final_project_2021;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.bot.final_project_2021.RoomDatabase.Data;
import com.bot.final_project_2021.RoomDatabase.DataRoomDatabase;

import java.util.Calendar;

public class AddIOActivity extends AppCompatActivity {
    private Data myNewData;
    private int mYear, mMonth, mDay;
    private EditText editDate;
    private EditText editTag;
    private EditText editMoney;
    private RadioGroup groupMoneyType;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_io);
        editDate = findViewById(R.id.editTextDate);
        editTag = findViewById(R.id.spinnerTag);
        editMoney = findViewById(R.id.editTextMoney);
        groupMoneyType = findViewById(R.id.moneyTypeGroup);
        editText = findViewById(R.id.editTextText);

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
        editDate.setText(date);

        final Button button = findViewById(R.id.addData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editMoney.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String mDate = editDate.getText().toString();
                    String mTag = editTag.getText().toString();
                    int mMoney = Integer.parseInt(editMoney.getText().toString());
                    Boolean mMoneyType = getMoneyType();
                    String mText = editText.getText().toString();
                    replyIntent.putExtra("Date", mDate);
                    replyIntent.putExtra("Tag", mTag);
                    replyIntent.putExtra("Money", mMoney);
                    replyIntent.putExtra("MoneyType", mMoneyType);
                    replyIntent.putExtra("Text", mText);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    private Boolean getMoneyType(){
        if(groupMoneyType.getCheckedRadioButtonId() == R.id.radioIncome){
            return true;
        }
        else if(groupMoneyType.getCheckedRadioButtonId() == R.id.radioOutcome) {
            return false;
        }
        Log.e("Add I/O activity", "function: \'getMoneyType\' Error");
        return false;
    }

}
