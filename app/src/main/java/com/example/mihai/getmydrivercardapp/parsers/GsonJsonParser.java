package com.example.mihai.getmydrivercardapp.parsers;

import com.example.mihai.getmydrivercardapp.constants.Formats;
import com.example.mihai.getmydrivercardapp.parsers.base.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class GsonJsonParser<T> implements JsonParser<T> {

    private final Class<T> mKlass;
    private final Class<T[]> mArrayKlass;
    private final Gson mGson;


    public GsonJsonParser(Class<T> klass, Class<T[]> arrayKlass) {
        mKlass = klass;
        mArrayKlass = arrayKlass;
        mGson = new GsonBuilder().setDateFormat(Formats.STRING_DATE_FORMAT).create();
    }


    @Override
    public List<T> fromJsonArray(String jsonString) {
        T[] result = mGson.fromJson(jsonString, mArrayKlass);
        return Arrays.asList(result);

    }

    @Override
    public T fromJson(String jsonString) {
        return mGson.fromJson(jsonString, mKlass);
    }

    @Override
    public String toJson(T object) {
        return mGson.toJson(object);
    }
}
