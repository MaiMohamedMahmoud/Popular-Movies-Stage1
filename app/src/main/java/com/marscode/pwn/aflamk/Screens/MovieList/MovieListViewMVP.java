package com.marscode.pwn.aflamk.Screens.MovieList;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.Screens.common.ObservableViewMVP;

import java.util.List;

interface MovieListViewMVP extends ObservableViewMVP<MovieListViewMVP.Listener> {
    interface Listener {
         void onClickItem(Context context, View view, Movies movie);
    }

    void getMenuView(MenuInflater inflater , Menu menu);

    void bindMovieList(List<Movies> moviesList);



}
