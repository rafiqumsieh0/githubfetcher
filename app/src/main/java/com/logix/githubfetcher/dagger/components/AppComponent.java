package com.logix.githubfetcher.dagger.components;
import com.logix.githubfetcher.dagger.modules.AppModule;
import com.logix.githubfetcher.dagger.modules.NetworkModule;
import com.logix.githubfetcher.dagger.modules.RoomModule;
import com.logix.githubfetcher.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {AppModule.class, NetworkModule.class, RoomModule.class})

public interface AppComponent {

    void inject(MainActivity mainActivity);

}