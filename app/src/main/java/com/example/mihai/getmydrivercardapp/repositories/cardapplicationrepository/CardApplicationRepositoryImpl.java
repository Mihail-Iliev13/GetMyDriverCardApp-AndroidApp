package com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository;

import com.example.mihai.getmydrivercardapp.StringConstants;
import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;
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
        Gson gson = new GsonBuilder().setDateFormat(StringConstants.STRING_DATE_FORMAT).create();
        return Arrays.asList(gson.fromJson(json, CardApplication[].class));
    }

    @Override
    public List<CardApplication> filterApplicationsByDate(String date) throws IOException {
        String url = mServerUrl + "/applications/filter/date/" + date;
        String json = mRequester.get(url);
        Gson gson = new GsonBuilder().setDateFormat(StringConstants.STRING_DATE_FORMAT).create();
        return Arrays.asList(gson.fromJson(json, CardApplication[].class));
    }

    @Override
    public List<CardApplication> filterApplicationsByStatus(CardApplicationStatus cardApplicationStatus) throws IOException {
        String url = mServerUrl + "/applications/filter/status/" + cardApplicationStatus.toString();
        String json = mRequester.get(url);
        Gson gson = new GsonBuilder().setDateFormat(StringConstants.STRING_DATE_FORMAT).create();
        return Arrays.asList(gson.fromJson(json, CardApplication[].class));
    }

    @Override
    public List<CardApplication> filterApplicationsByName(String name) throws IOException {
        String url = mServerUrl + "/applications/filter/name/" + name;
        String json = mRequester.get(url);
        Gson gson = new GsonBuilder().setDateFormat(StringConstants.STRING_DATE_FORMAT).create();
        return Arrays.asList(gson.fromJson(json, CardApplication[].class));
    }

    @Override
    public List<CardApplication> filterApplicationsByID(String ID) throws IOException {
        String url = mServerUrl + "/applications/filter/ID/" + ID;
        String json = mRequester.get(url);
        Gson gson = new GsonBuilder().setDateFormat(StringConstants.STRING_DATE_FORMAT).create();
        return Arrays.asList(gson.fromJson(json, CardApplication[].class));
    }
}
