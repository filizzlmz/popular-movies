package com.filizzlmzz.movielist.service;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.filizzlmzz.movielist.model.Movie;
import com.filizzlmzz.movielist.model.ResponseMovies;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskRestClient extends LiveData<List<Movie>> {

    String apiUrl = "https://api.themoviedb.org/3/movie/popular?api_key=f5dc38e841fe80209dc676baf4fd491f&language=en-US&page=1";


    private final Context context;

    public AsyncTaskRestClient(Context context){
        this.context = context;
        loadData();
    }


    private void loadData() {
        new AsyncTask<Void,Void,List<Movie>>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<Movie> doInBackground(Void... voids) {
                StringBuilder builder = new StringBuilder();
                List<Movie> data1 = new ArrayList<>();

                try {

                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    while (true){
                        String readLine = reader.readLine();
                        String data = readLine;

                        if(data == null){
                            break;
                        }

                        builder.append(data);



                        JsonParser parser = new JsonParser();
                        JsonElement mJson =  parser.parse(builder.toString());
                        Gson gson = new Gson();
                        ResponseMovies object = gson.fromJson(mJson, ResponseMovies.class);

                        data1.addAll(object.getResults());
                    }

                }catch (Exception e){

                }

                return data1;
            }


            @Override
            protected void onPostExecute(List<Movie> data) {

                setValue(data);

            }
        }.execute();
    }
}