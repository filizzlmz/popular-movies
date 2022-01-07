package com.filizzlmzz.movielist.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.filizzlmzz.movielist.R;
import com.filizzlmzz.movielist.databinding.FragmentDetailPageBinding;
import com.filizzlmzz.movielist.model.Movie;
import com.filizzlmzz.movielist.viewModel.DetailPageViewModel;

public class DetailPageFragment extends Fragment {

    private FragmentDetailPageBinding binding;
    private DetailPageViewModel viewModel;
    private Movie mMovie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail_page, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(DetailPageViewModel.class);

        binding.setMovie(mMovie);
        openBrowser();

        return view;
    }


    public void openBrowser(){

        binding.ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = viewModel.parseUrl(mMovie.getPosterPath());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    public void setData(Movie movie){
        mMovie = movie;
    }
}
