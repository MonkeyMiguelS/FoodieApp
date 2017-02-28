package com.jmperezra.foodie.di.modules;


import android.app.Activity;

import com.data.repository.api.ApiEndPoints;
import com.data.repository.api.login.LoginApiGatewayImpl;
import com.data.repository.api.login.models.mappers.ApiAuthMapper;
import com.data.repository.local.session.SessionLocalGatewayImpl;
import com.domain.commons.logger.Logger;
import com.domain.gateway.login.LoginApiGateway;
import com.domain.gateway.session.SessionLocalGateway;
import com.domain.usecase.login.LoginUseCase;
import com.jmperezra.foodie.di.scopes.SessionScope;
import com.presentation.AppViewInjector;
import com.presentation.UseCaseInvoker;
import com.presentation.modules.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule extends ActivityModule{

    public LoginModule(Activity activity) {
        super(activity);
    }

    @Provides
    @SessionScope
    public LoginPresenter provideLoginPresenter(UseCaseInvoker useCaseInvoker,
                                                AppViewInjector appViewInjector,
                                                Logger logger,
                                                LoginUseCase loginUseCase){
        return new LoginPresenter(useCaseInvoker, appViewInjector, logger, loginUseCase);
    }

    @Provides
    @SessionScope
    public LoginUseCase provideLoginUseCase(LoginApiGateway loginApiGateway, SessionLocalGateway sessionLocalGateway){
        return new LoginUseCase(loginApiGateway, sessionLocalGateway);
    }

    @Provides
    @SessionScope
    public LoginApiGateway provideApiLoginGateway(Logger logger,
                                                  ApiEndPoints apiEndPoints,
                                                  ApiAuthMapper apiAuthMapper){
        return new LoginApiGatewayImpl(logger, apiEndPoints, apiAuthMapper);
    }

    @Provides
    @SessionScope
    public SessionLocalGateway provideSessionLocalGateway(SessionLocalGatewayImpl impl){
        return impl;
    }
}
