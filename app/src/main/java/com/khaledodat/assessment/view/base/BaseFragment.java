package com.khaledodat.assessment.view.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.khaledodat.assessment.MyApp;
import com.khaledodat.assessment.eventbus.MessageEvent;
import com.khaledodat.assessment.utils.Constants;
import com.khaledodat.assessment.view.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<V extends ViewModel, D extends ViewDataBinding> extends Fragment {

    public MyApp myApp;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected V viewModel;

    protected D dataBinding;

    protected SharedPreferences prefs;

    protected abstract Class<V> getViewModel();

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void onTransitionEnd();

    protected abstract void trackEntranceEvent();

    protected View _rootView;


    protected MessageEvent noInternetMessageEvent = new MessageEvent();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        myApp = (MyApp) getContext().getApplicationContext();

        prefs = getContext().getSharedPreferences(Constants.SharedPreferencesKeys.PREFS_KEY_SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());


        noInternetMessageEvent.setData(false);
        noInternetMessageEvent.setMessageType(MessageEvent.MessageType.SHOW_HIDE_NO_INTERNET);
        noInternetMessageEvent.setSentTo(MainActivity.class);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);

        if (_rootView == null)
            _rootView = dataBinding.getRoot();

        return _rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
        trackEntranceEvent();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (nextAnim == 0) {
            onTransitionEnd();
            return null;
        }

        Animation anim = AnimationUtils.loadAnimation(getActivity(), nextAnim);

        if (anim == null)
            return null;

        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onTransitionEnd();
            }
        });

        return anim;
    }

    protected void showHideProgressBar(boolean isShown) {
        MessageEvent me = new MessageEvent();
        me.setSentTo(MainActivity.class);
        me.setMessageType(MessageEvent.MessageType.SHOW_HIDE_PROGRESS);
        me.setData(isShown);

        EventBus.getDefault().postSticky(me);
    }

    protected void showHideToolbar(boolean b, Class forWho) {
        MessageEvent me = new MessageEvent();
        me.setSentTo(forWho);
        me.setMessageType(MessageEvent.MessageType.ACTION_BAR_HIDE_SHOW);
        me.setData(b);

        EventBus.getDefault().postSticky(me);
    }
}
