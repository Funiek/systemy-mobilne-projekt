package com.example.trainingapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pull {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "martwy")
    public String martwy;
    @ColumnInfo(name = "negatyw")
    public String negatyw;
    @ColumnInfo(name = "sciaganie_drazka_gora")
    public String sciaganieDrazkaGora;
    @ColumnInfo(name = "ohp_hantelkami")
    public String ohpHantelkami;
    @ColumnInfo(name = "modlitewnik")
    public String modlitewnik;
    @ColumnInfo(name = "wioslowanie")
    public String wioslowanie;
    @ColumnInfo(name = "mlotki")
    public String mlotki;
    @ColumnInfo(name = "przedramiona")
    public String przedramiona;
}
