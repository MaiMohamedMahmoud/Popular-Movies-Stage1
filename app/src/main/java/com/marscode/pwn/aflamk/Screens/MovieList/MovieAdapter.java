package com.marscode.pwn.aflamk.Screens.MovieList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.R;
import com.marscode.pwn.aflamk.Screens.MovieListDetails.MovieDetailsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements MovieListItemMVPImpl.Listener {

    List<Movies> moviesList;
    Context context;

    MovieListItemMVPImpl mMovieListItemMVP;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setMoviesList(List<Movies> movies) {
        moviesList = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mMovieListItemMVP = new MovieListItemMVPImpl(LayoutInflater.from(parent.getContext()), parent);
        mMovieListItemMVP.registerListener(this);
        return new ViewHolder(mMovieListItemMVP);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);

    }


    @Override
    public int getItemCount() {
        if (moviesList == null) {
            return 0;
        } else {

            return moviesList.size();
        }
    }

    @Override
    public void onClickItem(Context context, View v, Movies movies) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra("Movies_Id", movies.getId());
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle transition = ActivityOptionsCompat
                .makeSceneTransitionAnimation((MainActivity) context, v, context.getString(R.string.shared_element_movieImage))
                .toBundle();

        context.startActivity(intent, transition);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(MovieListItemMVPImpl mMovieListItemMVP) {
            super(mMovieListItemMVP.getRootView());

        }


        public void bind(final int position) {
            mMovieListItemMVP.bindMovie(moviesList.get(position));

        }


    }
}

