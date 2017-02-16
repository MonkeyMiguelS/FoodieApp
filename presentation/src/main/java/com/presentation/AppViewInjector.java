package com.presentation;

public interface AppViewInjector {
    <V> V injectView(V view);
    <V> V nullObjectPatternView(V view);
}
