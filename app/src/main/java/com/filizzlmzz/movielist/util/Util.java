package com.filizzlmzz.movielist.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.filizzlmzz.movielist.R;

import java.util.Locale;

public class Util {

    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    @BindingAdapter("downloadUrl")
    public static void setMovieImage(ImageView imageView, String url){

        String fullUrl = BASE_IMAGE_URL + url;

        Glide.with(imageView.getContext())
                .load(fullUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_not_found)
                .into(imageView);
    }

    public static void setLanguage(String lang, Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("lang",lang);
        editor.commit();
    }

    public static String getLanguage(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String lang = sharedPref.getString("lang","");

        return lang;
    }

    public static void changeLanguage(Activity activity){
        String language = getLanguage(activity);

        if(language.equals("")){

            String phoneLang = Locale.getDefault().getISO3Language();
            if(phoneLang.equals("tr")){
                setLanguage("tr",activity);
            }
            else{
                setLanguage("en",activity);
            }
        }
        else {
            if(language.equals("tr")){
                setLanguage("tr",activity);
            }
            else {
                setLanguage("en",activity);
            }
        }

        language = getLanguage(activity);

        if(language.equals("tr")){
            setLocale("tr",activity);


        }
        else{
            setLocale("en",activity);
        }
    }

    public static void setLocale(String lang, Activity activity){
        Locale myLocale = new Locale(lang);
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}
