package com.jmperezra.foodie.di.modules;


import android.app.Activity;

import com.jmperezra.foodie.views.customviews.spinner.SpinnerLoading;
import com.jmperezra.foodie.views.customviews.spinner.SpinnerLoadingImp;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    protected final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return this.activity;
    }

    @Provides
    public SpinnerLoading provideSpinnerLoading(){
        return new SpinnerLoadingImp(activity);
    }
}
