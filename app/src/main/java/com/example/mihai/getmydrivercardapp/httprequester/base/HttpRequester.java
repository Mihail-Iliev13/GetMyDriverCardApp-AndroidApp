package com.example.mihai.getmydrivercardapp.httprequester.base;

import java.io.IOException;

public interface HttpRequester {
    String getWithRequestParam(String url, String name, String value) throws IOException;

    String get(String url) throws IOException;

    String post(String url, String body) throws IOException;

    String put(String url, String json) throws IOException;
}

