package com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.base.CardApplicationRepository;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class CardApplicationRepositoryImpl implements CardApplicationRepository {

    private HttpRequester mRequester;
    private String mServerUrl;
    private Gson mJsonParser;

    @Inject
    public CardApplicationRepositoryImpl (String serverUrl, HttpRequester httpRequester, Gson gson) {
        this.mRequester = httpRequester;
        this.mServerUrl = serverUrl;
        this.mJsonParser = gson;
    }

    @Override
    public List<CardApplication> getAllApplications() throws IOException {
        String url = mServerUrl + "/applications";
        String json = mRequester.get(url);
        return Arrays.asList(mJsonParser.fromJson(json, CardApplication[].class));
    }

    @Override
    public List<CardApplication> filterApplicationsByDate(String date) throws IOException {
        String url = mServerUrl + "/applications/filter/date/" + date;
        String json = mRequester.get(url);
        return Arrays.asList(mJsonParser.fromJson(json, CardApplication[].class));
    }

    @Override
    public List<CardApplication> filterApplicationsByStatus(CardApplicationStatus cardApplicationStatus) throws IOException {
        String url = mServerUrl + "/applications/filter/status/" + cardApplicationStatus.toString();
        String json = mRequester.get(url);
        return Arrays.asList(mJsonParser.fromJson(json, CardApplication[].class));
    }

    @Override
    public List<CardApplication> filterApplicationsByName(String name) throws IOException {
        String url = mServerUrl + "/applications/filter/name/" + name;
        String json = mRequester.get(url);
        return Arrays.asList(mJsonParser.fromJson(json, CardApplication[].class));
    }

    @Override
    public List<CardApplication> filterApplicationsByID(String ID) throws IOException {
        String url = mServerUrl + "/applications/filter/ID/" + ID;
        String json = mRequester.get(url);
        return Arrays.asList(mJsonParser.fromJson(json, CardApplication[].class));
    }

    @Override
    public CardApplication updateCardApplication(CardApplication cardApplication, CardApplicationStatus status) throws IOException {
        String url = mServerUrl + "/applications/" + cardApplication.getId();
        String statusJson = mJsonParser.toJson(status);
        String json = mRequester.put(url, statusJson);
        return mJsonParser.fromJson(json, CardApplication.class);
    }
}
