package com.jmperezra.foodie.commons;

import android.support.test.espresso.ViewInteraction;

import com.jmperezra.foodie.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class BasePageObject {


    public void closeKeyboard(ViewInteraction viewInteraction){
        viewInteraction.perform(closeSoftKeyboard());
    }

    public ViewInteraction getSpinnerLoading(){
        return onView(withId(R.id.spinner_progress_bar));
    }
}
