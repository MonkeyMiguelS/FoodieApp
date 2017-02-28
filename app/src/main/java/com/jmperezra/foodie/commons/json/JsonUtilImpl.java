package com.jmperezra.foodie.commons.json;

import com.domain.commons.JsonUtil;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import javax.inject.Inject;

public class JsonUtilImpl implements JsonUtil {

    private static final GsonBuilder gson = new GsonBuilder()
            //.registerTypeHierarchyAdapter(Collection.class, new CollectionAdapterGson())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    @Inject
    public JsonUtilImpl() {

    }

    /**
     * Convierte un objeto a Json.
     *
     * @param obj
     * @param <T>
     * @return
     */
    @Override
    public <T> String toJson(T obj){
        return gson.create().toJson(obj);
    }

    /**
     * Convierto una cadena con formato json a Objeto
     * @param json
     * @param typeClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T fromJson(String json, Class<T> typeClass){
        return gson.create().fromJson(json, typeClass);
    }

    /**
     * Convierto una cadena con formato json a Objeto
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> T fromJson(String json, Type type){
        return gson.create().fromJson(json, type);
    }
}
