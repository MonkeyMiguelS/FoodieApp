package com.jmperezra.foodie.di.components;

import com.jmperezra.foodie.di.modules.ReceiptModule;
import com.jmperezra.foodie.di.scopes.ActivityScope;
import com.jmperezra.foodie.views.modules.receipt.ReceiptActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
    modules = {
        ReceiptModule.class
    }
)
public interface ReceiptComponent {

    void inject(ReceiptActivity activity);
}
