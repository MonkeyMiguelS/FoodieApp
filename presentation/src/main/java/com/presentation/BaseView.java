package com.presentation;


import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView;

@ThreadDecoratedView
public interface BaseView {
    void showSpinner();
    void hideSpinner();
}
