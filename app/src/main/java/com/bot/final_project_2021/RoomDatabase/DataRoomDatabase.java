package com.bot.final_project_2021.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {MyData.class}, version = 1, exportSchema = true)
public abstract class Database extends RoomDatabase {
    public static final String _dbName = "RecordData.db";
    private static volatile Database instance;

    public static synchronized Database getInstance(Context context){
        if(instance == null){
            instance = Room
                       .databaseBuilder(context.getApplicationContext(), Database.class, _dbName)
                       .addMigrations(MARGIN_1to2)
                       .build();
        }
        return instance;
    }

    private static Database create(final Context context){
        return Room.databaseBuilder(context, Database.class, _dbName).build();
    }

    public static Migration MARGIN_1to2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE \"moneyTable\" ADD COLUMN money INTEGER NOT NULL DEFAULT 1000");
        }
    };

    public abstract DataDao getDataDao();   //設置對外接口
}
