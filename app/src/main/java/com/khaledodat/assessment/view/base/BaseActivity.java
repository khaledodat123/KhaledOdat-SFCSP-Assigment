package com.khaledodat.assessment.view.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.khaledodat.assessment.R;
import com.khaledodat.assessment.eventbus.MessageEvent;
import com.khaledodat.assessment.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class BaseActivity<D extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    protected SharedPreferences prefs;

    @SuppressWarnings("unused")
    public D dataBinding;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }

    ;

    @Override
    protected void onResume() {
        super.onResume();
        setupCustomFonts();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void showActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }

    public void hideActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        prefs = base.getSharedPreferences(Constants.SharedPreferencesKeys.PREFS_KEY_SHARED_PREFS_NAME, MODE_PRIVATE);

        setupCustomFonts();
        super.attachBaseContext(ViewPumpContextWrapper.wrap(updateBaseContextLocale(base)));
    }

    private Context updateBaseContextLocale(Context context) {
        String language = prefs.getString(Constants.SharedPreferencesKeys.PREFS_KEY_LANGUAGE, "en");
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale);
        }

        return updateResourcesLocaleLegacy(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    private void setupCustomFonts() {
        /** Uncomment below to activate font for whole system. Please go to BaseActivity.java and uncomment line in */

        String fontName = "fonts/source-sans-pro/SourceSansPro-Regular.ttf";
        if (prefs.getString(Constants.SharedPreferencesKeys.PREFS_KEY_LANGUAGE, "ar").equals("ar"))
            fontName = "fonts/Tajawal-Regular.ttf";

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(fontName)
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

}

