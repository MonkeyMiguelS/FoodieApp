package com.jmperezra.foodie.views.customviews.spinner;


import android.support.annotation.NonNull;

public interface SpinnerLoading {
    void show();
    void show(@NonNull final SpinnerLoadingListener listener);

    void dismiss();
    void dismiss(@NonNull final SpinnerLoadingListener listener);

}
