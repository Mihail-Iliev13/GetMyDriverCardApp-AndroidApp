package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.StringConstants;
import com.example.mihai.getmydrivercardapp.httprequester.OkHttpRequester;
import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpRequesteModule {
    @Provides
    public HttpRequester httpRequester(){
        return new OkHttpRequester();
    }

    @Provides
    @Named("baseServerUrl")
    public String baseServerUrl() {
        return StringConstants.BASE_SERVER_URL;
    }
}
