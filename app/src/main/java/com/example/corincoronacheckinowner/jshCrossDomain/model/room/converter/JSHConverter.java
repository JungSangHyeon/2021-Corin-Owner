package com.example.corincoronacheckinowner.jshCrossDomain.model.room.converter;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JSHConverter {
    public static <T> T fromStringToType(String json, Type type) { // v2
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
    public static <T> String fromTypeToString(T type) {
        Gson gson = new Gson();
        String json = gson.toJson(type);
        return json;
    }
}

//    public static <T> T fromStringToType(String value) { // fromStringToType v1. For Emergency
//        Type listType = new TypeToken<T>() {}.getType();
//        return new Gson().fromJson(value, listType);
//    }