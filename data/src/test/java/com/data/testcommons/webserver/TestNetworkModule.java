package com.data.testcommons.webserver;

import com.data.repository.api.ApiEndPoints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestNetworkModule {

    private MockWebServer mockWebServer;
    private Retrofit retrofit;

    public TestNetworkModule() {
        this.mockWebServer = new MockWebServer();
        this.retrofit = getRetrofitTest();
    }

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    private String getEndPoint() {
        return "/";
    }

    public MockWebServer getMockWebServer(){
        return this.mockWebServer;
    }

    public ApiEndPoints getApiService() {
        return getRetrofitTest().create(ApiEndPoints.class);
    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(1, TimeUnit.SECONDS);
        builder.connectTimeout(1, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(false);
        return builder.build();
    }

    private Retrofit getRetrofitTest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .baseUrl(mockWebServer.url(getEndPoint()))
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
