package com.khaledodat.assessment.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.khaledodat.assessment.R;
import com.khaledodat.assessment.databinding.ActivityMainBinding;
import com.khaledodat.assessment.eventbus.MessageEvent;
import com.khaledodat.assessment.utils.FragmentUtils;
import com.khaledodat.assessment.view.base.BaseActivity;
import com.khaledodat.assessment.view.fragment.LandPageFragment;

import static com.khaledodat.assessment.utils.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private static final String TAG = "MainActivity";

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(dataBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        FragmentUtils.replaceFragment(this, LandPageFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);

    }


    @Override
    public void onMessageEvent(MessageEvent event) {
        if (event.getSentTo() != this.getClass())
            return;

    }
}
