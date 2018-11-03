package com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

import java.io.IOException;
import java.util.List;

public interface CardApplicationRepository {
    List<CardApplication> getAllApplications() throws IOException;
}
