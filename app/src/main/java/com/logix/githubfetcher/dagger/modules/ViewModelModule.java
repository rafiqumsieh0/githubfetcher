package com.logix.githubfetcher.dagger.modules;


import com.logix.githubfetcher.dagger.ViewModelKey;
import com.logix.githubfetcher.viewmodels.CommitsViewModel;
import com.logix.githubfetcher.viewmodels.ViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CommitsViewModel.class)
    abstract ViewModel bindViewModel(CommitsViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory factory);
}
