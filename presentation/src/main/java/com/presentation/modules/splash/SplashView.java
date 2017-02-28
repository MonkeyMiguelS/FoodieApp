package com.presentation.modules.splash;

import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView;

@ThreadDecoratedView
public interface SplashView{
    void showSplash();
    void navigateToLogin();
}
