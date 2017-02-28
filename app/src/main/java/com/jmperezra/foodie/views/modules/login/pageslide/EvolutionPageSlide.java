package com.jmperezra.foodie.views.modules.login.pageslide;


import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.jmperezra.foodie.R;

public class EvolutionPageSlide implements PageSlide {

    public static EvolutionPageSlide getInstance(){
        return new EvolutionPageSlide();
    }

    private EvolutionPageSlide() { }

    @Override
    public @StringRes
    int getStringResId() {
        return R.string.title_discovery_page_slide;
    }

    public @DrawableRes
    int getImageResId(){
        return R.drawable.img_login_03;
    }
}
