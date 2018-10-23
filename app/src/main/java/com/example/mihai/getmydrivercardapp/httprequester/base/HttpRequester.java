package com.example.mihai.getmydrivercardapp.httprequester.base;

import java.io.IOException;

public interface HttpRequester {
    String get(String url) throws IOException;

    String post(String url, String body) throws IOException;

    String put(String url, String json) throws IOException;
}

