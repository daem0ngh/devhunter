package com.tutors.dev.devhunter;

import android.app.Application;
import android.content.Context;

import com.tutors.dev.devhunter.network.ApiManager;
import com.tutors.dev.devhunter.network.ApiService;

/**
 * Created by shs on 2016-01-09.
 */
public class MyApp extends Application {

    private static MyApp myApp;
    public static ApiService mApiService;

    public static Context get()	{ return myApp; }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

        //Fabric.with(this, new Crashlytics());
        mApiService = ApiManager.rebuildAdapter();

        return;
    }
}
