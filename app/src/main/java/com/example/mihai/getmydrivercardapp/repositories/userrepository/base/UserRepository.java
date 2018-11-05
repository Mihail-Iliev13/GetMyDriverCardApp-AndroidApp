package com.example.mihai.getmydrivercardapp.repositories.userrepository.base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

import java.io.IOException;

public interface UserRepository {

    User getUserByEmail(String email) throws IOException;
    User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException;
    User addUser(User user) throws IOException;
}
