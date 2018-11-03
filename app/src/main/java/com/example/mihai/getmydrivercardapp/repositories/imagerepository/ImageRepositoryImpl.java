package com.example.mihai.getmydrivercardapp.repositories.imagerepository;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.repositories.imagerepository.base.ImageRepository;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

public class ImageRepositoryImpl implements ImageRepository {


    private String mServerUrl;
    private HttpRequester mRequester;

    @Inject
    public ImageRepositoryImpl(String url, HttpRequester requester) {

        this.mServerUrl = url;
        this.mRequester = requester;
    }

    @Override
    public ImageModel saveImage(String email, ImageModel image) throws IOException {
        String url = mServerUrl + "/images/" + email;
        Gson gson = new Gson();
        String imageJson = gson.toJson(image);
        String response = mRequester.post(url, imageJson);
        return gson.fromJson(response, ImageModel.class);
    }
}
