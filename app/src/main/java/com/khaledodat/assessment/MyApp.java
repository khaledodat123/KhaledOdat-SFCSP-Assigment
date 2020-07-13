package com.khaledodat.assessment;

import android.app.Activity;
import android.app.Service;

import androidx.multidex.MultiDexApplication;

import com.khaledodat.assessment.di.components.DaggerAppComponent;
import com.kobakei.ratethisapp.RateThisApp;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;

public class MyApp extends MultiDexApplication implements HasActivityInjector, HasServiceInjector {
    private static final String TAG = "MyApp";

    private static MyApp sInstance;

    public static MyApp getAppContext() {
        return sInstance;
    }


    private static synchronized void setInstance(MyApp app) {
        sInstance = app;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;
    @Inject
    DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
        setupAppRater();

    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }


    private void setupAppRater() {
        RateThisApp.Config config = new RateThisApp.Config(5, Integer.MAX_VALUE);

        RateThisApp.init(config);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }

}
