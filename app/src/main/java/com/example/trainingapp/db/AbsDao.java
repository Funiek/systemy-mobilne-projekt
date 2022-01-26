package com.example.trainingapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AbsDao {
    @Query("SELECT * FROM abs ORDER BY uid DESC LIMIT 1")
    Abs getLastAbs();

    @Query("SELECT * FROM abs")
    Abs getAllAbs();

    @Insert
    void insertAbs(Abs... abs);

    @Delete
    void deleteAbs(Abs abs);
}
