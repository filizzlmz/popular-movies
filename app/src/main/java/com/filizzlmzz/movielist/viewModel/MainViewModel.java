package com.filizzlmzz.movielist.viewModel;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.filizzlmzz.movielist.util.Util;

public class MainViewModel extends ViewModel {

    public void setLocaleLanguage(Activity context){
        if(Util.getLanguage(context).equals("tr")){
            Util.setLocale("tr",context);
        }
        else{
            Util.setLocale("en",context);
        }
    }
}
