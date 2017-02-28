package com.jmperezra.foodie.views.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.jmperezra.foodie.FoodieApplication;
import com.jmperezra.foodie.di.ComponentsHelper;
import com.jmperezra.foodie.views.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Navigator navigator;

    public ComponentsHelper getComponentsHelper() {
        return ((FoodieApplication)getApplication()).getComponentsHelper();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
        setupLayout();
        bindViews();
    }

    protected abstract void setupActivityComponent();

    protected void setupLayout(){
        setContentView(getActivityLayout());
    }

    protected abstract @LayoutRes int getActivityLayout();

    protected void bindViews() {
        ButterKnife.setDebug(false);
        ButterKnife.bind(this);
    }
}
