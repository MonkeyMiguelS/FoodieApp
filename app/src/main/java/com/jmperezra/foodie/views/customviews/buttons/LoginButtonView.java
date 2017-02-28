package com.jmperezra.foodie.views.customviews.buttons;

import android.content.Context;
import android.util.AttributeSet;

import com.jmperezra.foodie.R;

public class LoginButtonView extends OkButtonView {

    public LoginButtonView(Context context) {
        super(context);
    }

    public LoginButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLabelButton() {
        return R.string.label_button_login;
    }
}
