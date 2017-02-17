package com.jmperezra.foodie.di.modules;

import android.app.Activity;

import com.data.repository.api.receipt.ReceiptLocalGatewayImpl;
import com.data.repository.local.receipt.ReceiptApiGatewayImpl;
import com.domain.gateway.receipt.ReceiptApiGateway;
import com.domain.gateway.receipt.ReceiptLocalGateway;
import com.domain.usecase.receipt.GetReceiptUseCase;
import com.jmperezra.foodie.di.scopes.ActivityScope;
import com.presentation.AppViewInjector;
import com.presentation.UseCaseInvoker;
import com.presentation.modules.receipt.ReceiptPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceiptModule extends ActivityModule {

    public ReceiptModule(Activity activity) {
        super(activity);
    }

    @Provides
    @ActivityScope
    ReceiptPresenter provideDemoPresenter(AppViewInjector appViewInjector,
                                          UseCaseInvoker useCaseInvoker,
                                          GetReceiptUseCase getReceiptUseCase){
        return new ReceiptPresenter(appViewInjector, useCaseInvoker, getReceiptUseCase);
    }

    @Provides
    @ActivityScope
    GetReceiptUseCase provideGetReceiptUseCase(ReceiptLocalGateway localGateway, ReceiptApiGateway apiGateway){
        return new GetReceiptUseCase(localGateway, apiGateway);
    }

    @Provides
    @ActivityScope
    ReceiptLocalGateway provideReceiptLocalGateway(ReceiptLocalGatewayImpl localGateway){
        return localGateway;
    }

    @Provides
    @ActivityScope
    ReceiptApiGateway provideReceiptApiGateway(ReceiptApiGatewayImpl apiGateway){
        return apiGateway;
    }
}
