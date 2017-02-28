package com.jmperezra.foodie.views.commons;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jmperezra.foodie.R;
import com.jmperezra.foodie.views.base.BaseActivity;


public class ActivityTransitions {

    public static void setFadeInOutAnimation(@NonNull Context context){
        ((BaseActivity)context).overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
    }

    public static void setSlideUpAnimation(@NonNull Context context){
        ((BaseActivity)context).overridePendingTransition(R.anim.anim_slide_up, R.anim.anim_no_change);
    }

    public static void setSlideRightAnimation(@NonNull Context context){
        ((BaseActivity)context).overridePendingTransition(R.anim.anim_slide_enter_from_right, R.anim.anim_no_change);
    }

    public static void setSlideExitToRightAnimation(@NonNull Context context){
        ((BaseActivity)context).overridePendingTransition(R.anim.anim_no_change, R.anim.anim_slide_exit_to_right);
    }
}
