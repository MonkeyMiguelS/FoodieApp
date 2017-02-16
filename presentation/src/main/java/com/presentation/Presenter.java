package com.presentation;

public abstract class Presenter<V> {

    private AppViewInjector appViewInjector;
    private V view;

    public Presenter(AppViewInjector appViewInjector) {
        this.appViewInjector = appViewInjector;
    }

    public void attachView(V view) {
        this.view = appViewInjector.injectView(view);
        onViewAttached();
    }

    public void detachView() {
        this.view = appViewInjector.nullObjectPatternView(view);
    }

    public V getView() {
        return view;
    }

    public abstract void onViewAttached();
}