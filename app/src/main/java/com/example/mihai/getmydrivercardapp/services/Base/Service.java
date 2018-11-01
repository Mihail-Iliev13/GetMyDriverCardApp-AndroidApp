package com.example.mihai.getmydrivercardapp.services.Base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.models.enums.UserRole;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Service {
    User getUserByEmail(String email) throws IOException;
    List<User> getAllUsers() throws IOException;
    User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException;
    CardApplication getPendingApplication(User user);
    List<CardApplication> getAllCardApplications() throws IOException;
    void filterApplicationsByName(String pattern);
    void filterApplicationsByID(String id);
    void filterApplicationsByDate(String pattern) throws ParseException;
    void filterApplicationsByStatus(String pattern);
    User addNewUser(String email, String password, UserRole client) throws IOException;
}
