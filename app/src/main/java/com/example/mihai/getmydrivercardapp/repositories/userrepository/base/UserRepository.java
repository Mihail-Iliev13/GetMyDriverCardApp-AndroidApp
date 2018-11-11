package com.example.mihai.getmydrivercardapp.repositories.userrepository.base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

import java.io.IOException;
import java.util.List;

public interface UserRepository {

    User getUserByEmail(String email) throws IOException;
    User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException;
    User addUser(User user) throws IOException;
    CardApplication getPendingApplication(User user) throws IOException;


    List<CardApplication> getApplications(String email) throws IOException;
}
