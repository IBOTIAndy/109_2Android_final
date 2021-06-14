package com.bot.final_project_2021.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;

@Entity(tableName = "money_table")
public class Money {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private int date_year;  //Date 年
    private int date_month; //月
    private int date_day;   //日
    private String tag;     //標籤
    private int money;      //金額
    private Boolean type;   //收入，支出 (1=收入, 2=支出)
    private String text;    //描述

    public Money(int date_year, int date_month, int date_day, String tag, int money, Boolean type, String text){
        this.date_year = date_year;
        this.date_month = date_month;
        this.date_day = date_day;
        this.tag = tag;
        this.money = money;
        this.type = type;
        this.text = text;
    }


    public void setId(int id) { this.id = id; }
    public void setDate_year(int date_year) { this.date_year = date_year; }
    public void setDate_month(int date_month) { this.date_month = date_month; }
    public void setDate_day(int date_day) { this.date_day = date_day; }
    public void setTag(String tag) { this.tag = tag; }
    public void setMoney(int money) { this.money = money; }
    public void setType(Boolean type) { this.type = type; }
    public void setText(String text) { this.text = text; }

    public int getId() { return id; }
    public int getDate_year() { return date_year; }
    public int getDate_month() { return date_month; }
    public int getDate_day() { return date_day; }
    public String getTag() { return tag; }
    public int getMoney() { return money; }
    public Boolean getType() { return type; }
    public String getText() { return text; }
}
