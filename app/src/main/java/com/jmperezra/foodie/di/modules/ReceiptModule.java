package com.jmperezra.foodie.di.modules;

import android.app.Activity;

import com.domain.usecase.receipt.GetReceiptUseCase;
import com.presentation.AppViewInjector;
import com.presentation.modules.receipt.ReceiptPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceiptModule extends ActivityModule {

    public ReceiptModule(Activity activity) {
        super(activity);
    }

    @Provides
    ReceiptPresenter provideDemoPresenter(AppViewInjector appViewInjector,
                                          GetReceiptUseCase getReceiptUseCase){
        return new ReceiptPresenter(appViewInjector, getReceiptUseCase);
    }
}
