package com.logix.githubfetcher.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectivityChecker {

    Application application;
    private static InternetConnectivityChecker INSTANCE;
    public InternetConnectivityChecker(Application application){
        this.application = application;

    }

    public static InternetConnectivityChecker getINSTANCE(Application application){
        if(INSTANCE == null){
            INSTANCE = new InternetConnectivityChecker(application);
        }
        return INSTANCE;
    }

    public boolean isConnectedToInternet(){
        ConnectivityManager cm =
                (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
