package com.jmperezra.foodie.di.components;


import com.jmperezra.foodie.di.modules.SplashModule;
import com.jmperezra.foodie.di.scopes.ActivityScope;
import com.jmperezra.foodie.views.modules.splash.SplashActivity;
import com.presentation.modules.splash.SplashPresenter;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
    modules = {
        SplashModule.class
    }
)
public interface SplashComponent {
    void inject(SplashActivity activity);

    SplashPresenter getSplashPresenter();
}
