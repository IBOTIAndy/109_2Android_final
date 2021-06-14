package com.bot.final_project_2021.Database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MoneyViewModel extends AndroidViewModel {
    private MoneyRepository mRepository;
    private LiveData<List<Money>> mAllMoneys;

    public MoneyViewModel(Application application){
        super(application);
        mRepository = new MoneyRepository(application);
        mAllMoneys = mRepository.getAllMoneys();
    }

    LiveData<List<Money>> getAllMoneys() { return mAllMoneys; }

    public void insert(Money money) { mRepository.insert(money); }
}
