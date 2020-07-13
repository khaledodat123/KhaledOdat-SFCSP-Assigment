package com.khaledodat.assessment.view.base;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.CallSuper;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.khaledodat.assessment.utils.Constants;
import com.khaledodat.assessment.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasServiceInjector;

public abstract class BaseService<V extends ViewModel> extends FirebaseMessagingService implements HasServiceInjector {


    @Inject
    DispatchingAndroidInjector<Service> serviceAndroidInjector;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected V viewModel;

    protected SharedPreferences prefs;

    protected abstract Class<V> getViewModel();

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceAndroidInjector;
    }

    @CallSuper
    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
        EventBus.getDefault().register(this);

        prefs = getApplicationContext().getSharedPreferences(Constants.SharedPreferencesKeys.PREFS_KEY_SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        viewModel = viewModelFactory.create(getViewModel());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){

    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
