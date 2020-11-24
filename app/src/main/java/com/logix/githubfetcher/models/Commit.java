package com.logix.githubfetcher.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Commit {

    @Ignore
    private transient Author author;
    @SerializedName("committer")
    @Ignore
    @Expose
    private Committer committer;
    @Expose
    @SerializedName("message")
    @PrimaryKey
    @NonNull
    private String message;
    @SerializedName("tree")
    @Ignore
    @Expose
    private Tree tree;
    @Ignore
    private transient String url;
    @Ignore
    private transient Integer commentCount;
    @Ignore
    private transient Verification verification;

//    @NonNull
//    @PrimaryKey
//    @ColumnInfo(name="commit_hash")
//    private String commitHash = tree.getSha();
//    @ColumnInfo(name="author")
//    private String authorName = committer.getName();
//    @ColumnInfo(name="commit_message")
//    private String commitMessage = message;
//
//    public String getAuthorName() {
//        return authorName;
//    }
//
//    public String getCommitHash() {
//        return commitHash;
//    }
//
//    public String getCommitMessage() {
//        return commitMessage;
//    }
//
//    public void setAuthorName(String authorName) {
//        this.authorName = authorName;
//    }
//
//    public void setCommitHash(String commitHash) {
//        this.commitHash = commitHash;
//    }
//
//    public void setCommitMessage(String commitMessage) {
//        this.commitMessage = commitMessage;
//    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

}