package com.bot.final_project_2021.RoomDatabase;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "moneyTable")
public class MyData {

    @PrimaryKey(autoGenerate = true)    //設置是否使ID自動累加 -> (1, 2, 3, ...)
    private int id;             //編號
    private String date;        //日期
    private String tag;         //分類
    private int money;          //錢
    private Boolean moneyType;  //收入/支出, moneyType==1 -> 收入; moneyType==0 -> 支出
    private String text;        //描述

    public MyData(String date, String tag, int money, Boolean moneyType, String text){
        this.date = date;
        this.tag = tag;
        this.money = money;
        this.moneyType = moneyType;
        this.text = text;
    }
    @Ignore
    public MyData(int id, String date, String tag, int money, Boolean moneyType, String text){
        this.id = id;
        this.date = date;
        this.tag = tag;
        this.money = money;
        this.moneyType = moneyType;
        this.text = text;
    }

    //將所有的資料都新增一個get/set
    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getDate(){ return date; }
    public void setDate(String date){ this.date = date; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }

    public Boolean getMoneyType() { return moneyType; }
    public void setMoneyType(Boolean moneyType) { this.moneyType = moneyType; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

}
