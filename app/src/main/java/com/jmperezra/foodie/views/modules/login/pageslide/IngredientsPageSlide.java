package com.jmperezra.foodie.views.modules.login.pageslide;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.jmperezra.foodie.R;

public class IngredientsPageSlide implements PageSlide {

    public static IngredientsPageSlide getInstance(){
        return new IngredientsPageSlide();
    }

    private IngredientsPageSlide() { }

    @Override
    public @StringRes
    int getStringResId() {
        return R.string.title_ingredients_page_slide;
    }

    public @DrawableRes
    int getImageResId(){
        return R.drawable.img_login_04;
    }
}
