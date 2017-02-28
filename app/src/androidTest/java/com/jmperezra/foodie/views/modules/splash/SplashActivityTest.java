package com.jmperezra.foodie.views.modules.splash;

import android.content.ComponentName;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.jmperezra.foodie.R;
import com.jmperezra.foodie.commons.EspressoDaggerMockRule;
import com.jmperezra.foodie.views.customviews.spinner.SpinnerLoading;
import com.jmperezra.foodie.views.modules.login.LoginActivity;
import com.presentation.modules.splash.SplashPresenter;
import com.presentation.modules.splash.SplashView;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SplashActivityTest{

    @Rule
    public EspressoDaggerMockRule rule = new EspressoDaggerMockRule();

    @Rule
    public IntentsTestRule<SplashActivity> mainActivityRule = new IntentsTestRule<>(SplashActivity.class, false, false);

    @Mock
    SplashPresenter presenter;

    @Mock
    SpinnerLoading spinnerLoading;

    @Test
    public void shouldLogoToBeVisible() throws Throwable{
        Mockito.doNothing().when(presenter).attachView(Matchers.<SplashView>any());
        Mockito.doNothing().when(presenter).onFinishSplash();

        mainActivityRule.launchActivity(new Intent());
        mainActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityRule.getActivity().showSplash();
            }
        });
        onView(withId(R.id.logoApp)).check(matches(isDisplayed()));
    }

    /**
     * fixme Refactorizar el thread para poder testearlo.
     * @throws Throwable
     */
    @Ignore
    @Test
    public void shouldFinishSplash() throws Throwable{
        Mockito.doNothing().when(presenter).attachView(Matchers.<SplashView>any());
        Mockito.doNothing().when(presenter).onFinishSplash();

        mainActivityRule.launchActivity(new Intent());
        mainActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityRule.getActivity().showSplash();
            }
        });
        verify(presenter, times(1)).onFinishSplash();
    }

    @Test
    public void shouldChangeViewToLogin() throws Throwable{
        Mockito.doNothing().when(presenter).attachView(Matchers.<SplashView>any());
        mainActivityRule.launchActivity(new Intent());
        mainActivityRule.getActivity().navigateToLogin();

        intended(hasComponent(new ComponentName(getTargetContext(), LoginActivity.class)));
        Assert.assertTrue(mainActivityRule.getActivity().isFinishing());
    }
}