package com.domain.commons;

import java.lang.reflect.Type;

public interface JsonUtil {

    <T> String toJson(T obj);

    <T> T fromJson(String json, Class<T> typeClass);

    <T> T fromJson(String json, Type type);

}
