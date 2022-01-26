package com.example.trainingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LegsDao {
    @Query("SELECT * FROM legs ORDER BY uid DESC LIMIT 1")
    Abs getLastLegs();

    @Query("SELECT * FROM legs")
    Abs getAllLegs();

    @Insert
    void insertLegs(Legs... legs);

    @Delete
    void deleteLegs(Legs legs);
}
