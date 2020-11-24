package com.logix.githubfetcher.typeconverters;

import com.google.gson.Gson;
import com.logix.githubfetcher.models.Commit;
import com.logix.githubfetcher.models.Committer_;

import androidx.room.TypeConverter;

// The classes are : Commit , Committer_

public class Converters {

    @TypeConverter
    public String fromCommitToJson(Commit commit){
        return new Gson().toJson(commit);
    }

    @TypeConverter
    public Commit fromJsonToCommit(String commitString){
        return new Gson().fromJson(commitString, Commit.class);
    }

    @TypeConverter
    public String fromCommitterToJson(Committer_ committer){
        return new Gson().toJson(committer);
    }

    @TypeConverter
    public Committer_ fromJsonToCommitter(String committerString){
        return new Gson().fromJson(committerString, Committer_.class);
    }
}
