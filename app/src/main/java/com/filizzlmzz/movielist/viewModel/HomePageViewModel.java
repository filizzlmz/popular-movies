package com.filizzlmzz.movielist.viewModel;


import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.filizzlmzz.movielist.model.Movie;
import com.filizzlmzz.movielist.service.AsyncTaskRestClient;
import com.filizzlmzz.movielist.util.Util;


import java.util.List;

public class HomePageViewModel extends AndroidViewModel {


    private AsyncTaskRestClient data;

    public HomePageViewModel(Application application){
        super(application);

        data = new AsyncTaskRestClient(application);
    }

    public LiveData<List<Movie>> getData() {
        return data;
    }


    public void setLanguage(Activity context, String lang){
        Util.setLanguage(lang,context);
    }

    public String getLanguage(Activity context){
        return Util.getLanguage(context);
    }

    public void changeLanguage(Activity context){
        Util.changeLanguage(context);
    }



}
