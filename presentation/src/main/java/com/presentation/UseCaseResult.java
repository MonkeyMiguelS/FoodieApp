package com.presentation;

public interface UseCaseResult<T> {
    void onResult(T result);
}
