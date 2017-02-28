package com.jmperezra.foodie.di.components;


import com.jmperezra.foodie.di.modules.LoginModule;
import com.jmperezra.foodie.di.scopes.SessionScope;
import com.jmperezra.foodie.views.modules.login.LoginActivity;

import dagger.Subcomponent;

@SessionScope
@Subcomponent(
    modules = {
        LoginModule.class
    }
)
public interface LoginComponent {
    void inject(LoginActivity activity);

}
