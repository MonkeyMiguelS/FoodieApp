package com.jmperezra.foodie.views.customviews.buttons;

import android.content.Context;
import android.util.AttributeSet;

import com.jmperezra.foodie.R;

public class OkButtonView extends ButtonView {

    public OkButtonView(Context context) {
        super(context);
    }

    public OkButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getBgColorResourceId() {
        return R.drawable.shape_button;
    }

    @Override
    public int getColorText() {
        return R.drawable.ok_text_color_button;
    }

    @Override
    public int getResourceTextSize() {
        return R.dimen.text_size_10;
    }

    @Override
    public int getLabelButton() {
        return R.string.label_ok_button;
    }
}
