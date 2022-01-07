package com.filizzlmzz.movielist.viewModel;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class DetailPageViewModel extends ViewModel {

    private final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    public Uri parseUrl(String url){

        url = BASE_IMAGE_URL + url;
        Uri uri = Uri.parse(url);
        return uri;

    }
}
