package com.example.mihai.getmydrivercardapp.httprequester;

import com.example.mihai.getmydrivercardapp.httprequester.base.HttpRequester;

import java.io.IOException;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequester implements HttpRequester {

    public OkHttpRequester() {

    }


    @Override
    public String getWithRequestParam(String url, String name, String value) throws IOException {
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url))
                .newBuilder()
                .addQueryParameter(name, value)
                .build();

        Request request = new Request.Builder()
                .get()
                .url(httpUrl)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client
                .newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client
                .newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String post(String url, String bodyString) throws IOException {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                bodyString
        );


        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request)
                .execute();


        String responseBody = response.body().string();
        return responseBody;
    }


    @Override
    public String put(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), json);

        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();

        Response response = client
                .newCall(request)
                .execute();

        return response.body().string();
    }
}

