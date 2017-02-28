package com.domain.models;


public final class SessionModel {

    private final AuthModel authModel;

    public SessionModel(AuthModel authModel) {
        this.authModel = authModel;
    }

    public AuthModel getAuthModel() {
        return authModel;
    }
}
