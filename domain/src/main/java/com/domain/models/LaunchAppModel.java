package com.domain.models;


public class LaunchAppModel {

    public final boolean isFirstTime;

    public LaunchAppModel(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }
}
