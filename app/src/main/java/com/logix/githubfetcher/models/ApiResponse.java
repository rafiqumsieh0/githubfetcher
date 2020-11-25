package com.logix.githubfetcher.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// Main response object we get from the Github API, here is an example of a response:
// https://api.github.com/repos/rafiqumsieh0/supervised-hebbian/commits


@Entity(tableName = "commits_table")
public class ApiResponse {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("sha")
    @Ignore
    private transient String sha;
    @SerializedName("node_id")
    @Ignore
    private transient String nodeId;
    @SerializedName("commit")
    @ColumnInfo(name = "commit")
    private Commit commit;
    @SerializedName("url")
    @Ignore
    private transient String url;
    @SerializedName("html_url")
    @Ignore
    private transient String htmlUrl;
    @SerializedName("comments_url")
    @Ignore
    private transient String commentsUrl;
    @SerializedName("author")
    @Ignore
    private transient Author_ author;
    @SerializedName("committer")
    @ColumnInfo(name = "committer")
    private Committer_ committer;
    @SerializedName("parents")
    @Ignore
    private transient List<Parent> parents = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public Author_ getAuthor() {
        return author;
    }

    public void setAuthor(Author_ author) {
        this.author = author;
    }

    public Committer_ getCommitter() {
        return committer;
    }

    public void setCommitter(Committer_ committer) {
        this.committer = committer;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

}