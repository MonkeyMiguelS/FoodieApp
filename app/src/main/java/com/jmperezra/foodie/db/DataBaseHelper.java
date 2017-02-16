package com.jmperezra.foodie.db;

import android.content.Context;

import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DataBaseHelper {

    private static final String NAME_DB = "ruralvia.realm";
    private static final int SCHEMA_VERSION = 1;
    private RealmConfiguration realmConfiguration;

    public static DataBaseHelper createInstance(Context context){
        return new DataBaseHelper(context);
    }

    private DataBaseHelper(Context context) {
        Realm.init(context);
    }

    public DataBaseHelper initalize(){
        buildRealmConfiguration();
        Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
        return this;
    }

    private DataBaseHelper buildRealmConfiguration(){
        realmConfiguration = new RealmConfiguration.Builder()
                .name(NAME_DB)
                //.encryptionKey(getEncryptionKey())
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        return this;
    }

    private byte[] getEncryptionKey(){
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        return key;
    }
}
