package com.logix.githubfetcher;

import android.app.Application;

import com.logix.githubfetcher.dagger.components.AppComponent;
import com.logix.githubfetcher.dagger.components.DaggerAppComponent;
import com.logix.githubfetcher.dagger.modules.AppModule;
//import com.logix.githubfetcher.dagger.components.DaggerAppComponent;

public class BaseApplication extends Application {

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}