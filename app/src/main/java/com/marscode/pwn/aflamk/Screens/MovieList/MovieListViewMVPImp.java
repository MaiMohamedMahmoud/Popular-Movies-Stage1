package com.marscode.pwn.aflamk.Screens.MovieList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marscode.pwn.aflamk.Data.MovieListner;
import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.R;
import com.marscode.pwn.aflamk.Screens.common.BaseViewMVP;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListViewMVPImp extends BaseViewMVP implements MovieListViewMVP {


    RecyclerView movieListRecycle;
    MovieAdapter mMovieAdapter;
    View rootview;
    private final List<Listener> mListeners = new ArrayList<>();

    public MovieListViewMVPImp(LayoutInflater inflater, ViewGroup group, MovieListner movieListner) {
        setRootView(inflater.inflate(R.layout.activity_main, group, false));

        movieListRecycle = findViewById(R.id.movie_list);
        mMovieAdapter = new MovieAdapter(getContext(),movieListner);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        movieListRecycle.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    @Override
    public void getMenuView(MenuInflater menuInflater , Menu menu) {
         menuInflater.inflate(R.menu.movies_menu, menu);
    }

    private Context getContext() {
        return getRootView().getContext();
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }


    @Override
    public void bindMovieList(List<Movies> moviesList) {
        mMovieAdapter.setMoviesList(moviesList);
        movieListRecycle.setAdapter(mMovieAdapter);
    }


}
