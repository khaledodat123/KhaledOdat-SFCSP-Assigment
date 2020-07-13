package com.khaledodat.assessment.di.builder;

import com.khaledodat.assessment.view.activity.MainActivity;
import com.khaledodat.assessment.view.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The module which provides the android injection service to Activities.
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract SplashActivity splashActivity();


}
