package com.jmperezra.foodie.views.modules.login.pageslide;


import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public interface PageSlide {
    @StringRes
    int getStringResId();
    @DrawableRes
    int getImageResId();
}
