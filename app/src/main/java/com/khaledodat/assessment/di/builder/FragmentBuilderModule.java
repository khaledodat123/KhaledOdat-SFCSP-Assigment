package com.khaledodat.assessment.di.builder;

import com.khaledodat.assessment.view.fragment.LandPageFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This builder provides android injector service to fragments
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract LandPageFragment contributeLandPageFragment();

}
