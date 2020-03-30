package com.marscode.pwn.aflamk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.marscode.pwn.aflamk.Data.MovieListner;
import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.Screens.MovieList.MovieAdapter;
import com.marscode.pwn.aflamk.Screens.MovieListDetails.MovieDetailsActivity;

import java.util.List;

public class FavouriteMovies extends AppCompatActivity implements MovieListner {

    private MovieViewModel mMovieViewModel;
    RecyclerView movieListfavouriteRecycle;
    MovieAdapter mMovieAdapter;
    LiveData<List<Movies>> mMoviesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);
        movieListfavouriteRecycle = findViewById(R.id.movie_favourite_list);
        mMovieAdapter = new MovieAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        movieListfavouriteRecycle.setLayoutManager(gridLayoutManager);

        mMovieViewModel = new MovieViewModel(getApplication());


        movieListfavouriteRecycle.setAdapter(mMovieAdapter);
        mMovieViewModel.getAllMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(@Nullable final List<Movies> movies) {
                // Update the cached copy of the Movies in the adapter.
                mMovieAdapter.setMoviesList(movies);
            }
        });
    }

    @Override
    public void onClickItem(Context context, View v, Movies movies) {

        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra("Movies_Id", movies.getId());
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle transition = ActivityOptionsCompat
                .makeSceneTransitionAnimation((FavouriteMovies) context, v, context.getString(R.string.shared_element_movieImage))
                .toBundle();

        context.startActivity(intent, transition);


    }
}
