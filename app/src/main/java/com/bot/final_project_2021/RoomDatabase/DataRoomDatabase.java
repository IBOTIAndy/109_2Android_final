package com.bot.final_project_2021.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Data.class}, version = 1, exportSchema = true)
public abstract class DataRoomDatabase extends RoomDatabase {

    public static final String _dbName = "RecordData.db";

    public abstract DataDao dataDao();

    private static volatile DataRoomDatabase INSTANCE;

    static DataRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null) {
            synchronized (DataRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataRoomDatabase.class, "money_database")
                            .fallbackToDestructiveMigration()
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DataDao mDao;
        String [] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(DataRoomDatabase db) {
            mDao = db.dataDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            return null;
        }
    }
}
