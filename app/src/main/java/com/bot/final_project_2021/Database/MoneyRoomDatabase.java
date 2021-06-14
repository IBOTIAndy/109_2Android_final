package com.bot.final_project_2021.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

@Database(entities = {Money.class}, version = 1, exportSchema = false)
public abstract class MoneyRoomDatabase extends RoomDatabase {

    public abstract MoneyDao moneyDao();

    private static MoneyRoomDatabase INSTANCE;

    static MoneyRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (MoneyRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MoneyRoomDatabase.class, "money_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    //新增後台資料
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final MoneyDao mDao;
        int i;
        int [] mYear = {2020, 2021, 2021},
                mMonth = {11, 2, 6},
                mDay = {21, 14, 4},
                mMoney = {22000, 80, 1490};
        String [] mTag = {"薪水", "食物", "交通"},
                mText = {"月薪", "便當", "捷運公車月票"};
        Boolean [] mType = {true, false, false};

        PopulateDbAsync(MoneyRoomDatabase db){ mDao = db.moneyDao();}

        @Override
        protected Void doInBackground(final Void... params){
            mDao.deleteAll();

            for(i=0; i<= mYear.length - 1; i++){
                Money money = new Money(mYear[i], mMonth[i], mDay[i], mTag[i], mMoney[i], mType[i], mText[i]);
                mDao.insert(money);
            }
            return null;
        }
    }
}
