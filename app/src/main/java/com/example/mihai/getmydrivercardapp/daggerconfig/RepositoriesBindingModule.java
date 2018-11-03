package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.parsers.base.JsonParser;
import com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.CardApplicationRepositoryImpl;
import com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.base.CardApplicationRepository;
import com.example.mihai.getmydrivercardapp.repositories.imagerepository.ImageRepositoryImpl;
import com.example.mihai.getmydrivercardapp.repositories.imagerepository.base.ImageRepository;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepositoryImpl;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepository;

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
                                         JsonParser<User> jsonParser) {
        return new UserRepositoryImpl(baseServerUrl, httpRequester, jsonParser);
    }

    @Provides
    @Singleton
    public ImageRepository imageRepository(@Named("baseServerUrl") String baseServerUrl,
                                          HttpRequester httpRequester) {
        return new ImageRepositoryImpl(baseServerUrl, httpRequester);
    }

    @Provides
    @Singleton
    public CardApplicationRepository cardApplicationRepository (@Named("baseServerUrl") String baseServerUrl,
                                                     HttpRequester httpRequester) {
        return new CardApplicationRepositoryImpl(baseServerUrl, httpRequester);
    }
}

