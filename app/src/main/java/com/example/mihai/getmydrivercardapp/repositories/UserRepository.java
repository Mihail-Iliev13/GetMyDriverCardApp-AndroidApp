package com.example.mihai.getmydrivercardapp.repositories;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.parsers.base.JsonParser;
import com.example.mihai.getmydrivercardapp.repositories.base.Repository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class UserRepository implements Repository {

    private String mServerUrl;
    private HttpRequester mRequester;
    private JsonParser<User> mJsonParser;

    @Inject
    public UserRepository (String url, HttpRequester requester,
                           JsonParser<User> jsonParser) {

        this.mServerUrl = url;
        this.mRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public User getUserByEmail(String email) throws IOException {
        String url = mServerUrl + "/" + email;
        String response = mRequester.get(url);
        return mJsonParser.fromJson(response);
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        String jsonArray = null;
        jsonArray = mRequester.get(mServerUrl);
        return mJsonParser.fromJsonArray(jsonArray);
    }

    @Override
    public User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException {
        return null;
    }


    @Override
    public User addUser(User user) throws IOException {
        String requestBody = mJsonParser.toJson(user);
        String responseBody = mRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }
}
