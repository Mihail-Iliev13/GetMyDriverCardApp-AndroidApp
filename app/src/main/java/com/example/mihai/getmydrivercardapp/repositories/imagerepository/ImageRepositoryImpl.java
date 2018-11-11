package com.example.mihai.getmydrivercardapp.repositories.imagerepository;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.repositories.imagerepository.base.ImageRepository;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class ImageRepositoryImpl implements ImageRepository {

    private String mServerUrl;
    private HttpRequester mRequester;
    private Gson mJsonParser;

    @Inject
    public ImageRepositoryImpl(String url, HttpRequester requester, Gson gson) {

        this.mServerUrl = url;
        this.mRequester = requester;
        this.mJsonParser = gson;
    }

    @Override
    public ImageModel saveImage(String email, ImageModel image) throws IOException {
        String url = mServerUrl + "/images/" + email;
        String imageJson = mJsonParser.toJson(image);
        String response = mRequester.post(url, imageJson);
        return mJsonParser.fromJson(response, ImageModel.class);
    }

    @Override
    public List<ImageModel> getImagesByApplicationID(int id) throws IOException {
        String url = mServerUrl + "/images/" + id;
        String response = mRequester.get(url);
        return Arrays.asList(mJsonParser.fromJson(response, ImageModel[].class));
    }
}
