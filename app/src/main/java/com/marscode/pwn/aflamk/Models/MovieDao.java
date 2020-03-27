package com.marscode.pwn.aflamk.Models;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies_table")
    LiveData<List<Movies>> getAll();

    @Query("SELECT * FROM movies_table where id LIKE  :id ")
    LiveData<Movies> isFavourite(int id);

    @Query("DELETE FROM movies_table WHERE id = :id")
    void deleteMovie(int id);


    @Insert
    void insertAll(Movies... movies);

    @Delete
    void delete(Movies movies);

    @Insert
    void insert(Movies movie);
}