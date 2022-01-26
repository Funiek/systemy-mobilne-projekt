package com.example.trainingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PushDao {
    @Query("SELECT * FROM push ORDER BY uid DESC LIMIT 1")
    Abs getLastPush();

    @Query("SELECT * FROM push")
    Abs getAllPush();

    @Insert
    void insertPush(Push... push);

    @Delete
    void deletePush(Push push);
}
