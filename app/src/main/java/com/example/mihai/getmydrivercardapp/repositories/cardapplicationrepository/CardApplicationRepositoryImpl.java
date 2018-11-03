package com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.base.CardApplicationRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CardApplicationRepositoryImpl implements CardApplicationRepository {

    private HttpRequester mRequester;
    private String mServerUrl;

    public CardApplicationRepositoryImpl (String serverUrl, HttpRequester httpRequester) {
        this.mRequester = httpRequester;
        this.mServerUrl = serverUrl;
    }

    @Override
    public List<CardApplication> getAllApplications() throws IOException {
        String url = mServerUrl + "/applications";
        String json = mRequester.get(url);
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        return Arrays.asList(gson.fromJson(json, CardApplication[].class));
    }
}
