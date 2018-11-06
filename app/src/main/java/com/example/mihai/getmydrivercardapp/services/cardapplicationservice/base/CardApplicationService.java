package com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface CardApplicationService {
    List<CardApplication> getAllApplications() throws IOException;
    List<CardApplication> filterApplicationsByName(String pattern) throws IOException;
    List<CardApplication> filterApplicationsByID(String id) throws IOException;
    List<CardApplication> filterApplicationsByDate(String pattern) throws ParseException, IOException;
    List<CardApplication> filterApplicationsByStatus(String pattern) throws IOException;
    void updateCardApplicationStatus(CardApplication cardApplication, String status) throws IOException;
}
