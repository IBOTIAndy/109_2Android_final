package com.bot.final_project_2021.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface MoneyDao {
    String table_name = "money_table";

    @Query("SELECT * from " + table_name + " ORDER BY money ASC")
    LiveData<List<Money>> getAlphabetizedMoney();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Money money);

    @Query("DELETE FROM " + table_name)
    void deleteAll();
}
