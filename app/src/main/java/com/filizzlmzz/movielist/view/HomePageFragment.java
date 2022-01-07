package com.filizzlmzz.movielist.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.filizzlmzz.movielist.R;
import com.filizzlmzz.movielist.adapter.MovieListAdapter;
import com.filizzlmzz.movielist.databinding.FragmentHomePageBinding;
import com.filizzlmzz.movielist.model.Movie;
import com.filizzlmzz.movielist.util.Util;
import com.filizzlmzz.movielist.viewModel.HomePageViewModel;


import java.util.List;

public class HomePageFragment extends Fragment {

    private FragmentHomePageBinding binding;
    private HomePageViewModel viewModel;
    private MovieListAdapter movieListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home_page, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(HomePageViewModel.class);

        if(viewModel.getLanguage(getActivity()).equals("tr")){
            binding.includeAppbar.btnLang.setActivated(true);

        }
        else{
            binding.includeAppbar.btnLang.setActivated(false);
        }

        setMovieList();
        changeLang();

        return view;
    }


    private void setMovieList(){

        viewModel.getData().observe(getViewLifecycleOwner(), response ->{
            List<Movie> movieList = response;

            movieListAdapter = new MovieListAdapter(getActivity(), movieList);
            movieListAdapter.setCallBack(new MovieListAdapter.CallBack() {
                @Override
                public void onClickMovie(Movie movie, int position) {
                    DetailPageFragment fragment = new DetailPageFragment();
                    fragment.setData(movie);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            binding.rvMovieList.setAdapter(movieListAdapter);
        });

    }


    private void changeLang(){
        binding.includeAppbar.btnLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!binding.includeAppbar.btnLang.isActivated()){
                    viewModel.setLanguage(getActivity(),"tr");
                }
                else{
                    viewModel.setLanguage(getActivity(),"en");
                }

                viewModel.changeLanguage(getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
