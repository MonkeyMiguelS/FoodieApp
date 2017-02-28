package com.jmperezra.foodie.di.modules;

import com.data.repository.api.ApiEndPoints;
import com.google.gson.Gson;
import com.jmperezra.foodie.BuildConfig;
import com.jmperezra.foodie.commons.network.HostSelectionInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        logging.setLevel(level);
        return logging;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(final HttpLoggingInterceptor loggingInterceptor,
                                            final HostSelectionInterceptor hostSelectionInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(hostSelectionInterceptor);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();

    }

    @Provides
    @Singleton
    @Named("EndPoint")
    public String provideEndPoint() {
        return BuildConfig.API_URL;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient, @Named("EndPoint") String endPoint) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(endPoint)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public ApiEndPoints provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiEndPoints.class);
    }

    @Provides
    @Singleton
    public HostSelectionInterceptor provideHostSelectionInterceptor(){
        return new HostSelectionInterceptor();
    }
}
