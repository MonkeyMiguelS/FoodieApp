package com.jmperezra.foodie.di.modules;


import android.app.Activity;

import com.data.repository.local.launchapp.LaunchAppLocalGatewayImpl;
import com.domain.gateway.launchapp.LaunchAppLocalGateway;
import com.domain.usecase.launchapp.SetLaunchAppUseCase;
import com.domain.usecase.splash.CheckSplashUseCase;
import com.jmperezra.foodie.di.scopes.ActivityScope;
import com.presentation.AppViewInjector;
import com.presentation.UseCaseInvoker;
import com.presentation.modules.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule extends ActivityModule{

    public SplashModule(Activity activity) {
        super(activity);
    }

    @ActivityScope
    @Provides
    public SplashPresenter provideSplashPresenter(UseCaseInvoker useCaseInvoker,
                                                  AppViewInjector appViewInjector,
                                                  CheckSplashUseCase checkSplashUseCase,
                                                  SetLaunchAppUseCase setLaunchAppUseCase){
        return new SplashPresenter(useCaseInvoker, appViewInjector, checkSplashUseCase, setLaunchAppUseCase);
    }

    @ActivityScope
    @Provides
    public CheckSplashUseCase provideCheckSplashUseCase(LaunchAppLocalGateway localGateway){
        return new CheckSplashUseCase(localGateway);
    }

    @ActivityScope
    @Provides
    public LaunchAppLocalGateway provideLaunchAppLocalGateway(LaunchAppLocalGatewayImpl impl){
        return impl;
    }

    @ActivityScope
    @Provides
    public SetLaunchAppUseCase provideSetLaunchAppUseCase(LaunchAppLocalGateway localGateway){
        return new SetLaunchAppUseCase(localGateway);
    }

}
