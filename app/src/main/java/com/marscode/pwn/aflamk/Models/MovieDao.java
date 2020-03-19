package com.marscode.pwn.aflamk.Models;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movies")
    List<Movies> getAll();

    @Query("SELECT * FROM Movies where id LIKE  :id ")
    Movies findById(int id);

    @Query("SELECT COUNT(*) from Movies")
    int countUsers();

    @Insert
    void insertAll(Movies... movies);

    @Delete
    void delete(Movies movies);
}