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
    ProgressBar spinner;
    LinearLayout refreshContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DaggerAppComponent.builder()
//                .appModule(new AppModule(getApplication()))
//                .build()
//                .inject(this);


        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(MainActivity.this));
        //((BaseApplication) getApplication()).getAppComponent().inject(this);
        commitsViewModel = ViewModelProviders.of(this).get(CommitsViewModel.class);
        commitsViewModel.getCommits();

        recyclerView = findViewById(R.id.commitsListRecyclerView);
        spinner = findViewById(R.id.mainActivityProgressBar);
        refreshContainer = findViewById(R.id.refreshCommitListContainer);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        CommitsAdapter adapter = new CommitsAdapter();

        recyclerView.setAdapter(adapter);
        commitsViewModel.commitsMutableLiveData.observe(this, new Observer<List<ApiResponse>>() {
            @Override
            public void onChanged(List<ApiResponse> commitModels) {
                adapter.setList(new ArrayList<ApiResponse>(commitModels));
            }
        });

        commitsViewModel.activityDataStatusLiveData.observe(this, new Observer<CommitsViewModel.ActivityDataStatus>() {
            @Override
            public void onChanged(CommitsViewModel.ActivityDataStatus activityDataStatus) {
                if(activityDataStatus.isError()){
                recyclerView.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                refreshContainer.setVisibility(View.VISIBLE);
                }
                else if(activityDataStatus.isLoading()){
                    recyclerView.setVisibility(View.GONE);
                    refreshContainer.setVisibility(View.GONE);
                    spinner.setVisibility(View.VISIBLE);
                }
                else{
                    spinner.setVisibility(View.GONE);
                    refreshContainer.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
