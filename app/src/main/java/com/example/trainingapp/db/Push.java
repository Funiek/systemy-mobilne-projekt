package com.example.trainingapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Push {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "lawka_plaska")
    public String lawkaPlaska;
    @ColumnInfo(name = "lawka_skosna")
    public String lawkaSkosna;
    @ColumnInfo(name = "triceps_linki")
    public String tricepsLinki;
    @ColumnInfo(name = "klata_linki")
    public String klataLinki;
    @ColumnInfo(name = "wznosy_boki_oburacz")
    public String wznosyBokiOburacz;
    @ColumnInfo(name = "kaptury")
    public String kaptury;
    @ColumnInfo(name = "arnoldki")
    public String arnoldki;
}
