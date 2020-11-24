package com.logix.githubfetcher.dagger.modules;

import android.app.Application;

import com.logix.githubfetcher.dao.CommitDao;
import com.logix.githubfetcher.localdatabases.CommitDatabase;
import com.logix.githubfetcher.repositories.MainRepository;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private CommitDatabase commitDatabase;

    public RoomModule(Application mApplication) {
        commitDatabase = Room.databaseBuilder(mApplication, CommitDatabase.class, "commits_database").build();
    }

    @Singleton
    @Provides
    CommitDatabase providesRoomDatabase() {
        return commitDatabase;
    }

    @Singleton
    @Provides
    CommitDao providesProductDao(CommitDatabase commitDatabase) {
        return commitDatabase.commitDao();
    }

}