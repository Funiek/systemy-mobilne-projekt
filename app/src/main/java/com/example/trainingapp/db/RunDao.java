package com.example.trainingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RunDao {
    @Query("SELECT * FROM run ORDER BY uid DESC LIMIT 1")
    Run getLastRun();

    @Query("SELECT * FROM run")
    List<Run> getAllRun();

    @Insert
    void insertRun(Run... run);

    @Delete
    void deleteRun(Run run);
}
