package com.example.trainingapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Legs {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "przysiad")
    public String przysiad;
    @ColumnInfo(name = "uginanie_nog_maszyna")
    public String uginanieNogMaszyna;
    @ColumnInfo(name = "wznosy_nog")
    public String wznosyNog;
    @ColumnInfo(name = "wykrok")
    public String wykrok;
}
