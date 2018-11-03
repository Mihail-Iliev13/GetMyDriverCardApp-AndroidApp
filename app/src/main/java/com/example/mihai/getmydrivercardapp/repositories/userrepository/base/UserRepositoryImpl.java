package com.example.mihai.getmydrivercardapp.repositories.userrepository.base;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.parsers.base.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class UserRepositoryImpl implements UserRepository {

    private String mServerUrl;
    private HttpRequester mRequester;
    private JsonParser<User> mJsonParser;

    @Inject
    public UserRepositoryImpl(String url, HttpRequester requester,
                              JsonParser<User> jsonParser) {

        this.mServerUrl = url;
        this.mRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public User getUserByEmail(String email) throws IOException {
        String url = mServerUrl + "/users/" + email;
        String response = mRequester.get(url);
        return mJsonParser.fromJson(response);
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        String jsonArray;
        jsonArray = mRequester.get(mServerUrl);
        return mJsonParser.fromJsonArray(jsonArray);
    }

    @Override
    public User updateUserCardApplication(String email, CardApplication cardApplication)
            throws IOException {

        String url = mServerUrl + "/users/" + email;
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        String json = gson.toJson(cardApplication);
        String response = mRequester.put(url, json);
        return gson.fromJson(response, User.class);
    }


    @Override
    public User addUser(User user) throws IOException {
        String requestBody = mJsonParser.toJson(user);
        String responseBody = mRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

}
