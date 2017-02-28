package com.data.repository.local.session.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LocalAuthModel extends RealmObject {

    @PrimaryKey
    private String id = LocalSessionModel.PK;
    public String alias;
    public String token;

    public LocalAuthModel() { }

    public LocalAuthModel(String alias, String token) {
        this.alias = alias;
        this.token = token;
    }

    public String getAlias() {
        return alias;
    }

    public String getToken() {
        return token;
    }
}
