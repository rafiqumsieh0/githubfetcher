package com.logix.githubfetcher.viewmodels;

import android.app.Application;
import android.util.Log;

import com.logix.githubfetcher.models.ApiResponse;
import com.logix.githubfetcher.repositories.MainRepository;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CommitsViewModel extends AndroidViewModel {
    private static final String TAG = "CommitsViewModel";
    public MutableLiveData<List<ApiResponse>> commitsMutableLiveData = new MutableLiveData<List<ApiResponse>>();
    public MutableLiveData<ActivityDataStatus> activityDataStatusLiveData = new MutableLiveData<ActivityDataStatus>(new ActivityDataStatus(true, false, ""));
    private MainRepository repository;
    private Application application;

    @Inject
    public CommitsViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        this.repository = new MainRepository(application);
    }


    public void getCommits(){
        Single<List<ApiResponse>> observable = repository
                                .getCommits()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o -> {
            activityDataStatusLiveData.setValue(new ActivityDataStatus(false, false,""));
            commitsMutableLiveData.setValue(o);
                    },
                e-> {
                    activityDataStatusLiveData.setValue(new ActivityDataStatus(false, true,e.getMessage().toString()));
                     });
    }

    public class ActivityDataStatus {
        private boolean isLoading;
        private boolean isError;
        private String error;

        public ActivityDataStatus(boolean isLoading, boolean isError, String error) {
            this.isLoading = isLoading;
            this.isError = isError;
            this.error = error;
        }

        public boolean isLoading() {
            return isLoading;
        }

        public void setLoading(boolean loading) {
            isLoading = loading;
        }

        public boolean isError() {
            return isError;
        }

        public void setError(boolean error) {
            isError = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
