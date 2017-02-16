package com.jmperezra.foodie.di;

import com.jmperezra.foodie.FoodieApplication;
import com.jmperezra.foodie.di.components.AppComponent;
import com.jmperezra.foodie.di.components.DaggerAppComponent;
import com.jmperezra.foodie.di.modules.AppModule;
import com.jmperezra.foodie.di.modules.NetworkModule;

public class ComponentsHelper {

    private FoodieApplication application;

    private AppComponent appComponent;

    public static ComponentsHelper createInstance(FoodieApplication application){
        return new ComponentsHelper(application);
    }

    private ComponentsHelper(FoodieApplication application){
        this.application = application;
    }

    public ComponentsHelper initialize(){
        this.buildAppComponent();
        return this;
    }

    private void buildAppComponent(){
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(application))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent(){
        if (appComponent == null){
            initialize();
        }
        return appComponent;
    }
}
