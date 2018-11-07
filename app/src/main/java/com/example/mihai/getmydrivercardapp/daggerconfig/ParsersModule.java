package com.example.mihai.getmydrivercardapp.daggerconfig;


import com.example.mihai.getmydrivercardapp.constants.Formats;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.parsers.GsonJsonParser;
import com.example.mihai.getmydrivercardapp.parsers.base.JsonParser;
import com.example.mihai.getmydrivercardapp.utils.bytearrayserializer.ByteArrayToBase64TypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule {

    @Provides
    public JsonParser<User> userheroJsonParser() {
        return new GsonJsonParser<>(User.class, User[].class);
    }

    @Provides
    public JsonParser<CardApplication> cardApplicationJsonParser() {
        return new GsonJsonParser<>(CardApplication.class, CardApplication[].class);
    }

    @Provides
    public Gson gson () {
        return new GsonBuilder()
                .setDateFormat(Formats.STRING_DATE_FORMAT)
                .registerTypeHierarchyAdapter(byte[].class,
                        new ByteArrayToBase64TypeAdapter()).create();
    }

}
