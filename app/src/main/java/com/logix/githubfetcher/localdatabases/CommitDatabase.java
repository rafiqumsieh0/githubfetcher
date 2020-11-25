package com.logix.githubfetcher.localdatabases;

import android.content.Context;

import com.logix.githubfetcher.dao.CommitDao;
import com.logix.githubfetcher.models.ApiResponse;
import com.logix.githubfetcher.typeconverters.Converters;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

// Database Instantiation
// Added Converters that convert between POJO and JSON to store in ROOM

@Database(entities = {ApiResponse.class}, version=2)
@TypeConverters(Converters.class)
public abstract class CommitDatabase extends RoomDatabase {
    private static CommitDatabase instance;
    public abstract CommitDao commitDao();
    public static synchronized CommitDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CommitDatabase.class, "commits_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
