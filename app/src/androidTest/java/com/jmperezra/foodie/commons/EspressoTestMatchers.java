package com.jmperezra.foodie.commons;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

public class EspressoTestMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }

    public static void checkVisibility(ViewInteraction view, ViewMatchers.Visibility visibility){
        view.check(matches(withEffectiveVisibility(visibility)));

    }

    public static void checkText(ViewInteraction view, String text){
        view.check(matches(withText(text)));
    }

    public static void checkNotText(ViewInteraction view, String text){
        view.check(matches(not(withText(text))));
    }

    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with "+childPosition+" child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }

    public static ViewInteraction getViewPagerPosition(Matcher<View> viewMatcher, int position){
        return onView(nthChildOf(viewMatcher, position));
    }

    public static void checkIsVisible(ViewInteraction viewInteraction){
        viewInteraction.check(matches(isDisplayed()));
    }

    public static void checkIsEnabled(ViewInteraction viewInteraction){
        viewInteraction.check(matches(isEnabled()));
    }

    public static void checkIsDisplayed(ViewInteraction viewInteraction){
        viewInteraction.check(matches(isDisplayed()));
    }

    public static void checkIsNotDisplayed(ViewInteraction viewInteraction){
        viewInteraction.check(matches(not(isDisplayed())));
    }

    public static Matcher<View> withCustomHint(final Matcher<String> stringMatcher) {
        return new BaseMatcher<View>() {
            @Override
            public void describeTo(Description description) {
            }

            @Override
            public boolean matches(Object item) {
                try {
                    Method method = item.getClass().getMethod("getHint");
                    return stringMatcher.matches(method.invoke(item));
                } catch (NoSuchMethodException e) {
                } catch (InvocationTargetException e) {
                } catch (IllegalAccessException e) {
                }
                return false;
            }
        };
    }

    public static Matcher<View> hasChildren(final Matcher<Integer> numChildrenMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                return view instanceof ViewGroup && numChildrenMatcher.matches(((ViewGroup)view).getChildCount());
            }

            /**
             * gets the description
             */
            @Override
            public void describeTo(Description description) {
                description.appendText(" a view with # children is ");
                numChildrenMatcher.describeTo(description);
            }
        };
    }

    public static void checkIsDisabled(ViewInteraction viewInteraction){
        viewInteraction.check(matches(not(isEnabled())));
    }

    public static void checkHint(ViewInteraction view, String text){
        view.check(matches(withCustomHint(is(text))));
    }
}
