package com.filizzlmzz.movielist.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filizzlmzz.movielist.R;
import com.filizzlmzz.movielist.databinding.ItemRowMovieListBinding;
import com.filizzlmzz.movielist.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>{

    private final int RES_ITEM_ROW_MOVIE_LIST = R.layout.item_row_movie_list;
    private List<Movie> mMovieList = new ArrayList<>();
    private Activity mActivity;

    private LayoutInflater mInflater;
    private GridLayoutManager mLayoutManager;

    private CallBack mCallBack;

    public MovieListAdapter(Activity context, List<Movie> movieList){
        this.mMovieList = movieList;
        this.mActivity = context;
        this.mInflater = LayoutInflater.from(mActivity);
        this.mLayoutManager = new GridLayoutManager(mActivity, 2);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowMovieListBinding binding = DataBindingUtil.inflate(
                mInflater.from(parent.getContext()),
                RES_ITEM_ROW_MOVIE_LIST, parent, false);


        return new MovieListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);

        ((MovieListViewHolder) holder).onBind(movie, position);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder{

        public ItemRowMovieListBinding itemRowMovieListBinding;

        public MovieListViewHolder(ItemRowMovieListBinding binding) {
            super(binding.getRoot());
            this.itemRowMovieListBinding = binding;
        }

        public void onBind(Movie movie, int position) {

            itemRowMovieListBinding.setData(movie);

            itemRowMovieListBinding.llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mCallBack != null){
                        mCallBack.onClickMovie(movie,position);
                    }
                }
            });
        }
    }

    public void setCallBack(CallBack callBack){
        mCallBack=callBack;

    }

    public interface CallBack {
        void onClickMovie(Movie movie, int position);
    }
}