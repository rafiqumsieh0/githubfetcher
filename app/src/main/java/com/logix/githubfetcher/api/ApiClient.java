package com.logix.githubfetcher.api;

import com.logix.githubfetcher.api.interfaces.CommitApi;
import com.logix.githubfetcher.models.ApiResponse;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


// Return a Singleton ApiClient to be used throughout the app.
// BASE_URL points to the Github API

public class ApiClient {
    private static final String BASE_URL = "https://api.github.com";
    private CommitApi commitApi;
    private static ApiClient INSTANCE;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                                        .build();
        commitApi = retrofit.create(CommitApi.class);

    }

    public static ApiClient getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }


    public Single<List<ApiResponse>> getCommits(String owner, String repo){
        return commitApi.getCommits(owner, repo);
    }
}
