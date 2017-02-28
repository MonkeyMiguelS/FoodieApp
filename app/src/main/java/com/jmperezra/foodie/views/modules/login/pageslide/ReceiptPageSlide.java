package com.jmperezra.foodie.views.modules.login.pageslide;


import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.jmperezra.foodie.R;

public class ReceiptPageSlide implements PageSlide {

    public static ReceiptPageSlide getInstance(){
        return new ReceiptPageSlide();
    }

    private ReceiptPageSlide() { }

    @Override
    public @StringRes
    int getStringResId() {
        return R.string.title_receipt_page_slide;
    }

    public @DrawableRes
    int getImageResId(){
        return R.drawable.img_login_01;
    }
}
