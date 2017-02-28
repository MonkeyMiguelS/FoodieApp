package com.data.repository.api.login.models;

public class ApiLoginModel{
    public final String username;
    public final String password;


    public ApiLoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
