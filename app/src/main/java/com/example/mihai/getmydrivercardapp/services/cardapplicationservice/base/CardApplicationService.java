package com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

import java.io.IOException;
import java.util.List;

public interface CardApplicationService {
    List<CardApplication> getAllApplications() throws IOException;
}
