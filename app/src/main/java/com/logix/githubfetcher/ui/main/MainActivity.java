package com.logix.githubfetcher.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.logix.githubfetcher.BaseApplication;
import com.logix.githubfetcher.R;
//import com.logix.githubfetcher.dagger.components.DaggerAppComponent;
import com.logix.githubfetcher.dagger.components.DaggerAppComponent;
import com.logix.githubfetcher.dagger.modules.AppModule;
import com.logix.githubfetcher.models.ApiResponse;
import com.logix.githubfetcher.viewmodels.CommitsViewModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    CommitsViewModel commitsViewModel;
    RecyclerView recyclerView;
    CommitsAdapter adapter;
    ProgressBar spinner;
    LinearLayout refreshContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpAppComponent();
        InjectActivity();
        setUpImageLoader();
        setUpUIElements();
        setUpRecyclerView();
        setUpRecyclerViewAdapter();
        getCommitsViewModel();
        getCommits();
        setCommitsListObserver();
        setActivityDataStatusObserver();

    }

    public void setUpAppComponent() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .build()
                .inject(this);
    }

    public void InjectActivity() {
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
    }

    public void setUpImageLoader(){
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(MainActivity.this));
    }

    public void setUpUIElements(){
        recyclerView = findViewById(R.id.commitsListRecyclerView);
        spinner = findViewById(R.id.mainActivityProgressBar);
        refreshContainer = findViewById(R.id.refreshCommitListContainer);
    }

    public void setUpRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        setUpRecyclerViewAdapter();
    }

    public void setUpRecyclerViewAdapter(){
        adapter = new CommitsAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void getCommits(){
        commitsViewModel.getCommits();
    }

    public void getCommitsViewModel(){
        commitsViewModel = ViewModelProviders.of(this).get(CommitsViewModel.class);
    }

    public void setCommitsListObserver(){
        commitsViewModel.commitsMutableLiveData.observe(this, new Observer<List<ApiResponse>>() {
            @Override
            public void onChanged(List<ApiResponse> commitModels) {
                adapter.setList(new ArrayList<ApiResponse>(commitModels));
            }
        });
    }

    public void setActivityDataStatusObserver(){
        // Load UI depending on the data received. If it is still loading, display a progress bar. If there is an error, display text
        // If data is loaded, display the RecyclerView.
        commitsViewModel.activityDataStatusLiveData.observe(this, new Observer<CommitsViewModel.ActivityDataStatus>() {
            @Override
            public void onChanged(CommitsViewModel.ActivityDataStatus activityDataStatus) {
                if (activityDataStatus.isError()) {
                    recyclerView.setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);
                    refreshContainer.setVisibility(View.VISIBLE);
                } else if (activityDataStatus.isLoading()) {
                    recyclerView.setVisibility(View.GONE);
                    refreshContainer.setVisibility(View.GONE);
                    spinner.setVisibility(View.VISIBLE);
                } else {
                    spinner.setVisibility(View.GONE);
                    refreshContainer.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
