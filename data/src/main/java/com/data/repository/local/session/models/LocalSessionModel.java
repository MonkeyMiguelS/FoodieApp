package com.data.repository.local.session.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LocalSessionModel extends RealmObject {

    public static final String PK = "0";

    @PrimaryKey
    private String id = PK;
    private LocalAuthModel localAuthModel;

    public LocalSessionModel() {
    }

    public LocalSessionModel(LocalAuthModel localAuthModel) {
        this.localAuthModel = localAuthModel;
    }

    public LocalAuthModel getLocalAuthModel() {
        return localAuthModel;
    }

}
