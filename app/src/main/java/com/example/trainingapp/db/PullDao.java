package com.example.trainingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PullDao {
    @Query("SELECT * FROM pull ORDER BY uid DESC LIMIT 1")
    Pull getLastPull();

    @Query("SELECT * FROM pull")
    List<Pull> getAllPull();

    @Insert
    void insertPull(Pull... pull);

    @Delete
    void deletePull(Pull pull);
}
