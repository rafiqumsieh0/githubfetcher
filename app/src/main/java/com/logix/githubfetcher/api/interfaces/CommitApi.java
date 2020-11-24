package com.logix.githubfetcher.api.interfaces;

import com.logix.githubfetcher.models.ApiResponse;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;


// Retrofit's REST API Interface that declares methods related to fetching Commits

public interface CommitApi {

    // Get commits if given a repo owner and a repo name
    @GET("repos/{owner}/{repo}/commits")
    public Single<List<ApiResponse>> getCommits(@Path("owner") String owner, @Path("repo") String repo);
}
