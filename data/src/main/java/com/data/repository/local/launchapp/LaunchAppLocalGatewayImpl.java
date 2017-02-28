package com.data.repository.local.launchapp;


import android.content.SharedPreferences;

import com.domain.exceptions.LocalGatewayException;
import com.domain.gateway.launchapp.LaunchAppLocalGateway;
import com.domain.models.LaunchAppModel;

import javax.inject.Inject;

public class LaunchAppLocalGatewayImpl implements LaunchAppLocalGateway {

    private SharedPreferences sharedPreferences;
    public static final String KEY_IS_FIRST_TIME_LAUNCH_APP = "key_is_first_time_launch_app";

    @Inject
    public LaunchAppLocalGatewayImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public LaunchAppModel obtainLaunchApp() throws LocalGatewayException {
        try {
            return new LaunchAppModel(sharedPreferences.getBoolean(KEY_IS_FIRST_TIME_LAUNCH_APP, true));
        }catch (Exception ex){
            throw new LocalGatewayException();
        }
    }

    @Override
    public void persistLaunchApp(LaunchAppModel launchApp) throws LocalGatewayException {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_IS_FIRST_TIME_LAUNCH_APP, launchApp.isFirstTime);
            editor.commit();
        }catch (Exception ex){
            throw new LocalGatewayException();
        }
    }
}
