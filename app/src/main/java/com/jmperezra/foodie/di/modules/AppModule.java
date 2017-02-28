package com.jmperezra.foodie.di.modules;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.domain.commons.JsonUtil;
import com.domain.commons.logger.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jmperezra.foodie.BuildConfig;
import com.jmperezra.foodie.FoodieApplication;
import com.jmperezra.foodie.commons.invoker.LogExceptionHandler;
import com.jmperezra.foodie.commons.invoker.PriorizableThreadPoolExecutor;
import com.jmperezra.foodie.commons.invoker.UseCaseInvokerImp;
import com.jmperezra.foodie.commons.invoker.UseCaseOutputThreadFactory;
import com.jmperezra.foodie.commons.invoker.UseCasePriorityBlockingQueue;
import com.jmperezra.foodie.commons.json.JsonUtilImpl;
import com.jmperezra.foodie.commons.logger.LoggerImpl;
import com.jmperezra.foodie.commons.outputs.BackThreadSpec;
import com.jmperezra.foodie.commons.outputs.MainThreadSpec;
import com.jmperezra.foodie.commons.outputs.SameThreadSpec;
import com.jmperezra.foodie.di.qualifiers.BackThread;
import com.jmperezra.foodie.di.qualifiers.SameThread;
import com.jmperezra.foodie.di.qualifiers.UiThread;
import com.jmperezra.foodie.views.navigation.Navigator;
import com.presentation.AppViewInjector;
import com.presentation.AppViewInjectorImpl;
import com.presentation.UseCaseInvoker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.panavtec.threaddecoratedview.views.ThreadSpec;

@Module
public class AppModule {

    private final FoodieApplication application;

    public AppModule(FoodieApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.application.getApplicationContext();
    }

    @Provides
    @Singleton
    public LogExceptionHandler provideLogExceptionHandler() {
        return new LogExceptionHandler();
    }

    @Provides
    @Singleton
    public ExecutorService provideExecutor(ThreadFactory threadFactory, BlockingQueue<Runnable> blockingQueue) {
        return new PriorizableThreadPoolExecutor(BuildConfig.CONCURRENT_INTERACTORS,
                BuildConfig.CONCURRENT_INTERACTORS, 0L, TimeUnit.MILLISECONDS, blockingQueue,
                threadFactory);
    }

    @Provides
    @Singleton
    public UseCaseInvoker provideUseCaseInvoker(ExecutorService executor, LogExceptionHandler logExceptionHandler) {
        return new UseCaseInvokerImp(executor, logExceptionHandler);
    }

    @Provides
    @Singleton
    public BlockingQueue<Runnable> provideBlockingQueue() {
        return new UseCasePriorityBlockingQueue(100);
    }

    @Provides
    @Singleton
    public ThreadFactory provideThreadFactory() {
        return new UseCaseOutputThreadFactory();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    @UiThread
    public ThreadSpec provideMainThread() {
        return new MainThreadSpec();
    }

    @Provides
    @Singleton
    @SameThread
    public ThreadSpec provideSameThread() {
        return new SameThreadSpec();
    }

    @Provides
    @Singleton
    @BackThread
    public ThreadSpec provideBackThread() {
        return new BackThreadSpec();
    }

    @Provides
    @Singleton
    public AppViewInjector provideAppViewInjector(@UiThread ThreadSpec threadSpec){
        return new AppViewInjectorImpl(threadSpec);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return new LoggerImpl();
    }

    @Provides
    @Singleton
    public Navigator provideNavigator(){
        return new Navigator();
    }

    @Provides
    @Singleton
    public JsonUtil provideJson(JsonUtilImpl jsonUtil){
        return jsonUtil;
    }
}
