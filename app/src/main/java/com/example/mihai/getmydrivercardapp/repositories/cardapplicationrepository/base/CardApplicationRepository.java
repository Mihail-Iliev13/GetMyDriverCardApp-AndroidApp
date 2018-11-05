package com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;

import java.io.IOException;
import java.util.List;

public interface CardApplicationRepository {
    List<CardApplication> getAllApplications() throws IOException;
    List<CardApplication> filterApplicationsByDate(String date) throws IOException;
    List<CardApplication> filterApplicationsByStatus(CardApplicationStatus cardApplicationStatus) throws IOException;
    List<CardApplication> filterApplicationsByName (String pattern) throws IOException;
    List<CardApplication> filterApplicationsByID (String ID) throws IOException;
}
