package com.filizzlmzz.movielist.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.filizzlmzz.movielist.R;
import com.filizzlmzz.movielist.databinding.ActivityMainBinding;
import com.filizzlmzz.movielist.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.setLocaleLanguage(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomePageFragment()).commit();
    }
}