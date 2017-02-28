package com.jmperezra.foodie.commons;

import android.support.test.InstrumentationRegistry;

import com.jmperezra.foodie.FoodieApplication;
import com.jmperezra.foodie.di.components.AppComponent;
import com.jmperezra.foodie.di.modules.AppModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

public class EspressoDaggerMockRule extends DaggerMockRule<AppComponent> {

    public EspressoDaggerMockRule() {
        super(AppComponent.class, new AppModule(getApp()));

        set(new ComponentSetter<AppComponent>() {
            @Override public void setComponent(AppComponent component) {
                getApp().getComponentsHelper().setAppComponent(component);
            }
        });
    }

    private static FoodieApplication getApp() {
        return (FoodieApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
}
