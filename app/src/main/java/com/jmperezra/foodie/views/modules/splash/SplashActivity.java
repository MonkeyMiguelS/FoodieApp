package com.jmperezra.foodie.views.modules.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jmperezra.foodie.R;
import com.jmperezra.foodie.commons.threads.ThreadCommon;
import com.jmperezra.foodie.di.modules.SplashModule;
import com.jmperezra.foodie.views.base.BaseActivity;
import com.presentation.modules.splash.SplashPresenter;
import com.presentation.modules.splash.SplashView;

import javax.inject.Inject;

import butterknife.Bind;

public class SplashActivity extends BaseActivity implements SplashView {

    public static final int MS_SPLASH_DELAYED = 2000;

    @Inject
    SplashPresenter presenter;

    @Bind(R.id.logoApp)
    TextView logoApp;

    @Override
    protected void setupActivityComponent() {
        getComponentsHelper().getAppComponent().plus(new SplashModule(this)).inject(this);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
    }

    @Override
    public void showSplash() {
        logoApp.setVisibility(View.VISIBLE);
        ThreadCommon.postDelayed(MS_SPLASH_DELAYED, new ThreadCommon.FinishDelayListener() {
            @Override
            public void onFinishDelay() {
                presenter.onFinishSplash();
            }
        });
    }

    @Override
    public void navigateToLogin(){
        navigator.navigateToLoginView(this);
        finish();
    }
}
