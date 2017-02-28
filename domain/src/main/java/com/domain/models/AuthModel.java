package com.domain.models;


public class AuthModel {

    private final String alias;
    private final String password;
    private String token;

    public AuthModel(String alias, String password) {
        this.alias = alias;
        this.password = password;
        this.token = null;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
