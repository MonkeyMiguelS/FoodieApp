package com.jmperezra.foodie.commons.threads;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

public class ThreadCommon {

    public interface FinishDelayListener{
        void onFinishDelay();
    }

    public static void postDelayed(@NonNull int millisecondsDelay, @NonNull final FinishDelayListener finishDelayListener){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                finishDelayListener.onFinishDelay();
            }
        }, millisecondsDelay);
    }
}
