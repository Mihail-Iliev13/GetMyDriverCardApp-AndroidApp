package com.example.mihai.getmydrivercardapp.daggerconfig;


import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.parsers.GsonJsonParser;
import com.example.mihai.getmydrivercardapp.parsers.base.JsonParser;

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

}
