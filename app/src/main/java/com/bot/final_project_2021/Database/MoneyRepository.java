package com.bot.final_project_2021.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MoneyRepository {
    private MoneyDao mMoneyDao;
    private LiveData<List<Money>> mAllMoneys;

    MoneyRepository(Application application){
        MoneyRoomDatabase db = MoneyRoomDatabase.getDatabase(application);
        mMoneyDao = db.moneyDao();
        mAllMoneys = mMoneyDao.getAlphabetizedMoney();
    }

    LiveData<List<Money>> getAllMoneys(){ return mAllMoneys; }

    public void insert (Money money){ new insertAsyncTask(mMoneyDao).execute(money); }

    private static class insertAsyncTask extends AsyncTask<Money, Void, Void>{
        private MoneyDao mAsyncTaskDao;

        insertAsyncTask(MoneyDao dao){ mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final Money... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
