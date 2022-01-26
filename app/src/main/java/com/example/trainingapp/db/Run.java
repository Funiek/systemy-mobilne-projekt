package com.example.trainingapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Run {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "odleglosc")
    public String odleglosc;
    @ColumnInfo(name = "czas")
    public String czas;
}
