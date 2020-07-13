package com.khaledodat.assessment.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;

import com.khaledodat.assessment.R;
import com.khaledodat.assessment.databinding.ActivitySplashBinding;
import com.khaledodat.assessment.eventbus.MessageEvent;
import com.khaledodat.assessment.view.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {


    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //TODO: wait for 3 seconds and then go to login or main fragments
        Disposable a = Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(this::decideWhereToGo);
    }

    @Override
    public void onMessageEvent(MessageEvent event) {
        if (event.getSentTo() != this.getClass())
            return;

    }

    private void decideWhereToGo() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

}
