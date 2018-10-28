package com.example.mihai.getmydrivercardapp.repositories.base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

import java.io.IOException;
import java.util.List;

public interface Repository {

    User getUserByEmail(String email) throws IOException;
    List<User> getAllUsers() throws IOException;
    User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException;
    User addUser(User user) throws IOException;
}
