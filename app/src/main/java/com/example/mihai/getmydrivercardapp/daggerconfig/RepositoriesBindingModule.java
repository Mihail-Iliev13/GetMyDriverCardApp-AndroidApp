package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.CardApplicationRepositoryImpl;
import com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.base.CardApplicationRepository;
import com.example.mihai.getmydrivercardapp.repositories.imagerepository.ImageRepositoryImpl;
import com.example.mihai.getmydrivercardapp.repositories.imagerepository.base.ImageRepository;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.UserRepositoryImpl;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepository;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesBindingModule {
    @Provides
    @Singleton
    public UserRepository userRepository(@Named("baseServerUrl") String baseServerUrl,
                                         HttpRequester httpRequester,
                                         Gson jsonParser) {
        return new UserRepositoryImpl(baseServerUrl, httpRequester, jsonParser);
    }

    @Provides
    @Singleton
    public ImageRepository imageRepository(@Named("baseServerUrl") String baseServerUrl,
                                          HttpRequester httpRequester, Gson jsonParser) {
        return new ImageRepositoryImpl(baseServerUrl, httpRequester, jsonParser);
    }

    @Provides
    @Singleton
    public CardApplicationRepository cardApplicationRepository (@Named("baseServerUrl") String baseServerUrl,
                                                     HttpRequester httpRequester, Gson jsonParser) {
        return new CardApplicationRepositoryImpl(baseServerUrl, httpRequester, jsonParser);
    }
}

