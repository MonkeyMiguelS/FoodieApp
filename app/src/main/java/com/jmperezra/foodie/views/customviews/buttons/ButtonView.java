package com.jmperezra.foodie.views.customviews.buttons;


import android.content.Context;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.LinearLayout;

public abstract class ButtonView extends Button{

    public ButtonView(Context context) {
        super(context);
        initialize();
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize(){
        setupView();
        setBackgroundResource(getBgColorResourceId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setTextColor(getResources().getColorStateList(getColorText(), getContext().getTheme()));
        }else{
            setTextColor(getResources().getColorStateList(getColorText()));
        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(getResourceTextSize()));
        setText(getContext().getText(getLabelButton()));
    }

    private void setupView(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        setLayoutParams(params);
    }

    public void disableButton(){
        setClickable(false);
        setEnabled(false);
        getBackground().setAlpha(128);
    }

    public void enableButton(){
        setClickable(true);
        setEnabled(true);
        getBackground().setAlpha(255);
    }

    public abstract @DrawableRes
    int getBgColorResourceId();

    public abstract int getColorText();

    public abstract @DimenRes
    int getResourceTextSize();

    public abstract @StringRes
    int getLabelButton();

}
