package com.example.trainingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LegsDao {
    @Query("SELECT * FROM legs ORDER BY uid DESC LIMIT 1")
    Legs getLastLegs();

    @Query("SELECT * FROM legs")
    List<Legs> getAllLegs();

    @Insert
    void insertLegs(Legs... legs);

    @Delete
    void deleteLegs(Legs legs);
}
