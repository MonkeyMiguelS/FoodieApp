package com.data.repository.local.launchapp.models;


public class LocalLaunchAppModel {

    public static final int PRIMARY_KEY_CONST = 0;

    public int id = PRIMARY_KEY_CONST;

    public boolean firstTime;

    public LocalLaunchAppModel() {

    }

    public LocalLaunchAppModel(int id, boolean firstTime) {
        this.id = id;
        this.firstTime = firstTime;
    }

    public int getId() {
        return id;
    }

    public boolean isFirstTime() {
        return firstTime;
    }
}
