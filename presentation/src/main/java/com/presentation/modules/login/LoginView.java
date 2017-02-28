package com.presentation.modules.login;


import com.presentation.BaseView;

import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView;

@ThreadDecoratedView
public interface LoginView extends BaseView {
    void releaseLocalStorage();
    void loginSuccess();

    void showLoginError(String message);
    void hideLoginError();

    void disableButtonLogin();
    void enableButtonLogin();

    void showNetworkError();
    void showInternalError();
}
