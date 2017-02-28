package com.data.testcommons;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.robolectric.RuntimeEnvironment;

public class SharedPreferenceFactory {

    public static SharedPreferences get(){
        return PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);
    }

}
