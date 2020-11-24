package com.logix.githubfetcher.dao;

import com.logix.githubfetcher.models.ApiResponse;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Single;


// CRUD methods for Room
@Dao
public interface CommitDao {

    @Insert
    Completable insert(ApiResponse githubCommit);

    @Insert
    Completable insertCommits(List<ApiResponse> githubCommits);

    @Update
    Completable update(ApiResponse githubCommit);

    @Delete
    Completable delete(ApiResponse githubCommit);

    @Query("SELECT * from commits_table LIMIT 25")
    Single<List<ApiResponse>> getAllCommits();

    @Query("DELETE FROM commits_table")
    Completable deleteAllCommits();
}
