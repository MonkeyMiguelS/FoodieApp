package com.jmperezra.foodie;

import android.app.Application;

import com.jmperezra.foodie.db.DataBaseHelper;
import com.jmperezra.foodie.di.ComponentsHelper;
import com.jmperezra.foodie.logger.LoggerImpl;

import butterknife.ButterKnife;


public class FoodieApplication extends Application {

    public static FoodieApplication instance;
    private ComponentsHelper componentsHelper;
    private DataBaseHelper dataBaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        componentsHelper = ComponentsHelper.createInstance(this).initialize();
        initializeBindViews();
        initializeLogger();
    }

    public void releaseLocalStorage(){
        dataBaseHelper = DataBaseHelper
                .createInstance(this)
                .initalize();
    }

    private void initializeBindViews(){
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }

    private void initializeLogger(){
        LoggerImpl.initialize();
    }

    public ComponentsHelper getComponentsHelper(){
        if (componentsHelper == null){
            componentsHelper = ComponentsHelper.createInstance(this).initialize();
        }
        return componentsHelper;
    }

    public DataBaseHelper getDataBaseHelper(){
        return dataBaseHelper;
    }
}
