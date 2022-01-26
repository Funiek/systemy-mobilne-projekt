package com.example.trainingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PushDao {
    @Query("SELECT * FROM push ORDER BY uid DESC LIMIT 1")
    Push getLastPush();

    @Query("SELECT * FROM push")
    List<Push> getAllPush();

    @Insert
    void insertPush(Push... push);

    @Delete
    void deletePush(Push push);
}
