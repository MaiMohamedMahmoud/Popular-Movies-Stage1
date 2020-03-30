package com.marscode.pwn.aflamk.Screens.MovieList;

import android.content.Context;
import android.view.View;

import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.Screens.common.ObservableViewMVP;

public interface MovieListViewItemMVP extends ObservableViewMVP<MovieListViewItemMVP.Listener> {
    interface Listener {
        void onClickItem(Context context, View view, Movies movie);
    }

    void bindMovie(Movies movie);
}
