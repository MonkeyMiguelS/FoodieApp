package com.jmperezra.foodie.di.components;

import android.content.SharedPreferences;

import com.data.repository.api.ApiEndPoints;
import com.jmperezra.foodie.di.modules.AppModule;
import com.jmperezra.foodie.di.modules.ReceiptModule;
import com.jmperezra.foodie.di.modules.NetworkModule;
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

    ReceiptComponent plus(ReceiptModule module);

    UseCaseInvoker getUseCaseInvoker();
    SharedPreferences getSharedPreferences();
    ApiEndPoints getApiService();
    AppViewInjector getAppViewInjector();
    Retrofit getRetrofit();
    OkHttpClient getOkHttpClient();
}
