package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.parsers.base.JsonParser;
import com.example.mihai.getmydrivercardapp.repositories.UserRepository;
import com.example.mihai.getmydrivercardapp.repositories.base.Repository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {
    @Provides
    @Singleton
    public Repository userRepository(@Named("baseServerUrl") String baseServerUrl,
                                     HttpRequester httpRequester,
                                     JsonParser<User> jsonParser) {
        return new UserRepository(baseServerUrl, httpRequester, jsonParser);
    }

}

