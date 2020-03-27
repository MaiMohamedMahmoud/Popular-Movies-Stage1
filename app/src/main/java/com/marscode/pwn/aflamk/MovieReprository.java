package com.marscode.pwn.aflamk;

import android.app.Application;

import com.marscode.pwn.aflamk.Models.MovieDao;
import com.marscode.pwn.aflamk.Models.MovieDatabase;
import com.marscode.pwn.aflamk.Models.Movies;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieReprository {

    private LiveData<List<Movies>> movieList;
    private MovieDao mMovieDao;


    public MovieReprository(Application application) {
        MovieDatabase movieDatabase = MovieDatabase.getInstance(application);
        mMovieDao = movieDatabase.movieDao();
        movieList = mMovieDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    public LiveData<List<Movies>> getAllMovies() {
        return movieList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // Delete movie by Id

    public void deleteMovie(int id) {
        MovieDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.deleteMovie(id);
        });
    }

    public LiveData<Movies> isFavourite(int id) {
        return mMovieDao.isFavourite(id);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Movies Movie) {
        MovieDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.insert(Movie);
        });
    }


}
