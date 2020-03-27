package com.marscode.pwn.aflamk;

import android.app.Application;
import android.util.Log;

import com.marscode.pwn.aflamk.Models.Movies;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieViewModel extends AndroidViewModel {

    MovieReprository mMovieReprository;
    private LiveData<List<Movies>> mMoviesList;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        mMovieReprository = new MovieReprository(application);
        mMoviesList = mMovieReprository.getAllMovies();
    }

    public LiveData<List<Movies>> getAllMovies() {
        return mMoviesList;
    }

    public void deleteMovie(int id) {
        mMovieReprository.deleteMovie(id);
    }

    public LiveData<Movies> isFavourite(int id) {
        return mMovieReprository.isFavourite(id);
    }

    public void insert(Movies movies) {
        mMovieReprository.insert(movies);
    }
}
