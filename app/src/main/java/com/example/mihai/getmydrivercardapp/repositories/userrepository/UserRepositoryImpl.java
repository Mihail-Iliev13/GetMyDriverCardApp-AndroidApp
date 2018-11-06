package com.example.mihai.getmydrivercardapp.repositories.userrepository;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepository;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

public class UserRepositoryImpl implements UserRepository {

    private String mServerUrl;
    private HttpRequester mRequester;
    private Gson mJsonParser;

    @Inject
    public UserRepositoryImpl(String url, HttpRequester requester,
                              Gson jsonParser) {

        this.mServerUrl = url;
        this.mRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public User getUserByEmail(String email) throws IOException {
        String url = mServerUrl + "/users/" + email;
        String response = mRequester.get(url);
        return mJsonParser.fromJson(response, User.class);
    }


    @Override
    public User updateUserCardApplication(String email, CardApplication cardApplication)
            throws IOException {

        String url = mServerUrl + "/users/" + email;
        String json = mJsonParser.toJson(cardApplication);
        String response = mRequester.put(url, json);
        return mJsonParser.fromJson(response, User.class);
    }


    @Override
    public User addUser(User user) throws IOException {
        String url = mServerUrl + "/users";
        String requestBody = mJsonParser.toJson(user);
        String responseBody = mRequester.post(url, requestBody);
        return mJsonParser.fromJson(responseBody, User.class);
    }

    @Override
    public CardApplication getPendingApplication(User user) throws IOException {
        String url = mServerUrl + "/users/pending/" + user.getEmail();
        String json = mRequester.get(url);
        return mJsonParser.fromJson(json, CardApplication.class);
    }

}
