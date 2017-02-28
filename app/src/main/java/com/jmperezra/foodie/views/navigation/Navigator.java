package com.jmperezra.foodie.views.navigation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jmperezra.foodie.views.commons.ActivityTransitions;
import com.jmperezra.foodie.views.modules.login.LoginActivity;
import com.jmperezra.foodie.views.modules.receipt.ReceiptActivity;

public class Navigator {

    public Navigator() {
    }

    public void navigateToLoginView(@NonNull Context context) {
        context.startActivity(LoginActivity.getCallingIntent(context));
        ActivityTransitions.setFadeInOutAnimation(context);
    }

    public void navigateToReceiptView(@NonNull Context context) {
        context.startActivity(ReceiptActivity.getCallingIntent(context));
        ActivityTransitions.setFadeInOutAnimation(context);
    }
}
