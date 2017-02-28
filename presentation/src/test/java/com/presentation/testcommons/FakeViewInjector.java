package com.presentation.testcommons;

import com.presentation.AppViewInjector;

public class FakeViewInjector implements AppViewInjector {

    @Override public <V> V injectView(V view) {
        return view;
    }

    @Override public <V> V nullObjectPatternView(V view) {
        return view;
    }
}
