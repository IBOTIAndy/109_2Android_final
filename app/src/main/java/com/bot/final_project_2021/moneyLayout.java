package com.bot.final_project_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.bot.final_project_2021.RoomDatabase.MyAdapter;
import com.bot.final_project_2021.RoomDatabase.Data;

public class moneyLayout extends AppCompatActivity {
    MyAdapter myAdapter;
    Data nowSelectedData;//取得在畫面上顯示中的資料內容
    EditText editDate;
    EditText editTag;
    EditText editMoney;
    EditText editMoneyType;
    EditText editText;

}