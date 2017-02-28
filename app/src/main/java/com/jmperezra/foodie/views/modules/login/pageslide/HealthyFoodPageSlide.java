package com.jmperezra.foodie.views.modules.login.pageslide;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.jmperezra.foodie.R;

public class HealthyFoodPageSlide implements PageSlide {

    public static HealthyFoodPageSlide getInstance(){
        return new HealthyFoodPageSlide();
    }

    private HealthyFoodPageSlide() { }

    @Override
    public @StringRes
    int getStringResId() {
        return R.string.title_healthy_food_page_slide;
    }

    public @DrawableRes
    int getImageResId(){
        return R.drawable.img_login_02;
    }
}
