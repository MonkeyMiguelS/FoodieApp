package com.data.repository.local.launchapp;

import android.content.SharedPreferences;

import com.data.testcommons.SharedPreferenceFactory;
import com.data.testcommons.TestApplication;
import com.domain.models.LaunchAppModel;
import com.jmperezra.data.BuildConfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(application = TestApplication.class, constants = BuildConfig.class, sdk = 23)
public class LaunchAppLocalGatewayImplTest {

    LaunchAppLocalGatewayImpl launchAppLocalGateway;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LaunchAppLocalGatewayImplTest() {
        this.sharedPreferences = SharedPreferenceFactory.get();
        this.editor = this.sharedPreferences.edit();
        this.launchAppLocalGateway = new LaunchAppLocalGatewayImpl(this.sharedPreferences);
    }

    @Before
    public void setUp() throws Exception {
        this.editor.clear();
    }

    @Test
    public void shouldReturnTrueWhenFirstTime() throws Exception{
        assertIsFirstTime(true);
    }

    @Test
    public void shouldReturnFalseWhenIsNotFirstTime() throws Exception{
        assertIsFirstTime(false);
    }

    @Test
    public void shouldReturnTrueWithoutValue() throws Exception{
        LaunchAppModel launchAppModel = launchAppLocalGateway.obtainLaunchApp();
        Assert.assertTrue(launchAppModel.isFirstTime);
    }

    @Test
    public void shouldReturnValue() throws Exception{
        boolean isFirstTime = false;

        LaunchAppModel model = new LaunchAppModel(isFirstTime);
        launchAppLocalGateway.persistLaunchApp(model);
        LaunchAppModel launchAppModel = launchAppLocalGateway.obtainLaunchApp();
        Assert.assertEquals(launchAppModel.isFirstTime, isFirstTime);
    }


    private void assertIsFirstTime(boolean isFirstTime) throws Exception{
        this.editor.putBoolean(LaunchAppLocalGatewayImpl.KEY_IS_FIRST_TIME_LAUNCH_APP, isFirstTime);
        this.editor.commit();

        LaunchAppModel launchAppModel = launchAppLocalGateway.obtainLaunchApp();
        Assert.assertEquals(launchAppModel.isFirstTime, isFirstTime);
    }
}