package com.example.trainingapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Abs.class,Legs.class,Pull.class,Push.class,Run.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AbsDao absDao();
    public abstract LegsDao legsDao();
    public abstract PullDao pullDao();
    public abstract PushDao pushDao();
    public abstract RunDao runDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        if(INSTANCE == null) INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_TRAINLY").allowMainThreadQueries().build();

        return INSTANCE;
    }
}
