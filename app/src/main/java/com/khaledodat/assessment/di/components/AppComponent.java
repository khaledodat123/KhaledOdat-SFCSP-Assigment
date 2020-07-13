package com.khaledodat.assessment.di.components;

import android.app.Application;

import com.khaledodat.assessment.MyApp;
import com.khaledodat.assessment.di.builder.ActivityBuilderModule;
import com.khaledodat.assessment.di.builder.ServiceBuilderModule;
import com.khaledodat.assessment.di.module.AppModule;
import com.khaledodat.assessment.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * The main application component which initializes all the dependent modules
 */
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        ServiceBuilderModule.class,
        RepositoryModule.class})

public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MyApp myApp);
}