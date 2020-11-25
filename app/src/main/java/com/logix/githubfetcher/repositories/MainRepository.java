package com.logix.githubfetcher.repositories;

import android.app.Application;
import android.util.Log;

import com.logix.githubfetcher.R;
import com.logix.githubfetcher.api.ApiClient;
import com.logix.githubfetcher.dao.CommitDao;
import com.logix.githubfetcher.localdatabases.CommitDatabase;
import com.logix.githubfetcher.models.ApiResponse;
import com.logix.githubfetcher.utils.InternetConnectivityChecker;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {
    private CommitDao commitDao;
    private Single<List<ApiResponse>> commits;
    private InternetConnectivityChecker connectivityChecker;
    private ApiClient apiClient;
    Application application;

    @Inject
    public MainRepository(Application application){
        this.application = application;
        connectivityChecker = InternetConnectivityChecker.getINSTANCE(application);
        CommitDatabase database = CommitDatabase.getInstance(application);
        commitDao = database.commitDao();
        apiClient = ApiClient.getINSTANCE();
        commits = getCommits();

    }

    public void insert(ApiResponse commit){
        commitDao.insert(commit)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void update(ApiResponse commit){
    }

    public void delete(ApiResponse commit){
    }


    // Fetch the list of commits
    // If we have an internet connection, fetch the commits from the API and save them locally in Room
    // If we have no internet connection, try grabbing the previously saved commits from Room
    public Single<List<ApiResponse>> getCommits(){
        //Single<List<ApiResponse>> commits;
        if(connectivityChecker.isConnectedToInternet()){
            commits = apiClient.getCommits(application.getResources().getString(R.string.github_repository_owner),
                                            application.getResources().getString(R.string.github_repository_name ));
            commits.subscribeOn(Schedulers.io()).subscribe(commitsList -> {
                Completable deleteCommits = commitDao.deleteAllCommits();
                deleteCommits.subscribeOn(Schedulers.io()).subscribe(()->{
                    Log.d("Delete Success","Success");
                    Completable insertCommits = commitDao.insertCommits(commitsList);
                    insertCommits.subscribeOn(Schedulers.io()).subscribe(()->{Log.d("Insert Success","Success");}, e->Log.e("InsertError",e.getMessage()));
                    }, e->Log.e("DeleteError",e.getMessage()));

            });
        }
        else{
            commits = commitDao.getAllCommits();
        }
        return commits;
    }


}
