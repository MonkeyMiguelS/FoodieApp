package com.jmperezra.foodie.views.modules.login;

import android.support.test.espresso.ViewInteraction;
import android.view.View;

import com.jmperezra.foodie.R;
import com.jmperezra.foodie.commons.BasePageObject;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class LoginPageObject extends BasePageObject {

    public ViewInteraction getAliasInput(){
        return onView(withId(R.id.inputAlias));
    }

    public ViewInteraction getPasswordInput(){
        return onView(withId(R.id.inputPassword));
    }

    public ViewInteraction getErrorText(){
        return onView(withId(R.id.viewTextError));
    }

    public ViewInteraction getLoginButton(){
        return onView(withId(R.id.buttonLogin));
    }

    public ViewInteraction getAliasInputLayout(){
        return onView(withId(R.id.textInputLayoutAlias));
    }

    public ViewInteraction getPasswordInputLayout(){
        return onView(withId(R.id.textInputLayoutPassword));
    }

    public Matcher<View> getViewPagerId(){
        return withId(R.id.vpLogin);
    }

    public ViewInteraction getViewPager(){
        return onView(getViewPagerId());
    }

    public ViewInteraction getPagerIndicator(){
        return onView(withId(R.id.viewIndicator));
    }

    public void writeAliasInput(String inputAlias){
        getAliasInput().perform(typeText(inputAlias));
    }

    public void writePasswordInput(String inputPassword){
        getPasswordInput().perform(typeText(inputPassword));
    }

    public void clickLogin(){
        getLoginButton().perform(click());
    }
}
