package com.marscode.pwn.aflamk.Screens.MovieList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.R;
import com.marscode.pwn.aflamk.Screens.common.BaseViewMVP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.marscode.pwn.aflamk.Data.ApiUtils.Base_image_URl;
import static com.marscode.pwn.aflamk.Data.ApiUtils.image_size;

public class MovieListItemMVPImpl extends BaseViewMVP implements MovieListViewItemMVP {

    List<Listener> mListener = new ArrayList<>();

    Movies movies;
    ImageView movie_image;

    public MovieListItemMVPImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.movie_list_item, parent, false));
        movie_image = findViewById(R.id.movie_image);

    }

    private Context getContext() {
        return getRootView().getContext();
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public void registerListener(MovieListViewItemMVP.Listener listener) {
        mListener.add(listener);
    }

    @Override
    public void unregisterListener(MovieListViewItemMVP.Listener listener) {
        mListener.remove(listener);
    }

    @Override
    public void bindMovie(Movies movie) {
        movies = movie;
        Picasso.with((getContext())).
                load(getImageFullPath(movie.getPosterPath())).
                into(movie_image);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : mListener) {
                    listener.onClickItem(getContext(), getRootView(), movies);
                }
            }
        });

    }

    public String getImageFullPath(String posterPath) {
        Log.i("URL", Base_image_URl + image_size + posterPath);
        return Base_image_URl + image_size + posterPath;

    }


}
