package com.jmperezra.foodie.di.components;

import android.content.SharedPreferences;

import com.data.repository.api.ApiEndPoints;
import com.jmperezra.foodie.commons.network.HostSelectionInterceptor;
import com.jmperezra.foodie.di.modules.AppModule;
import com.jmperezra.foodie.di.modules.LoginModule;
import com.jmperezra.foodie.di.modules.NetworkModule;
import com.jmperezra.foodie.di.modules.ReceiptModule;
import com.jmperezra.foodie.di.modules.SplashModule;
import com.presentation.AppViewInjector;
import com.presentation.UseCaseInvoker;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(
    modules = {
        AppModule.class,
        NetworkModule.class,
    }
)
public interface AppComponent {

    SplashComponent plus(SplashModule module);
    ReceiptComponent plus(ReceiptModule module);
    LoginComponent plus(LoginModule module);

    UseCaseInvoker getUseCaseInvoker();
    SharedPreferences getSharedPreferences();
    ApiEndPoints getApiService();
    AppViewInjector getAppViewInjector();
    Retrofit getRetrofit();
    OkHttpClient getOkHttpClient();
    HostSelectionInterceptor getHostSelectionInterceptor();
}
