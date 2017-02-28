package com.jmperezra.foodie.views.modules.receipt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.domain.models.ReceiptModel;
import com.jmperezra.foodie.FoodieApplication;
import com.jmperezra.foodie.R;
import com.jmperezra.foodie.di.modules.ReceiptModule;
import com.jmperezra.foodie.views.base.BaseActivity;
import com.presentation.modules.receipt.ReceiptPresenter;
import com.presentation.modules.receipt.ReceiptView;

import javax.inject.Inject;

public class ReceiptActivity extends BaseActivity implements ReceiptView{

    @Inject
    ReceiptPresenter receiptPresenter;

    public static Intent getCallingIntent(@NonNull Context context){
        return new Intent(context, ReceiptActivity.class);
    }

    @Override
    protected void setupActivityComponent() {
        ((FoodieApplication)getApplication())
                .getComponentsHelper()
                .getAppComponent()
                .plus(new ReceiptModule(this))
                .inject(this);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_receipt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void renderReceiptView(ReceiptModel receiptModel) {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showApiError() {

    }

    @Override
    public void showInternalError() {

    }
}
