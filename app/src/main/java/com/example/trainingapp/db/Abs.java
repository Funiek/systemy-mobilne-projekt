package com.example.trainingapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Abs {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "deska")
    public String deska;
    @ColumnInfo(name = "linka_skos")
    public String linkaSkos;
    @ColumnInfo(name = "robak")
    public String robak;
}
