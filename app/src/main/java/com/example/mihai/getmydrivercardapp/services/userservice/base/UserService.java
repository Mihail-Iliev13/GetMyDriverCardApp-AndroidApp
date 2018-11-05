package com.example.mihai.getmydrivercardapp.services.userservice.base;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.models.enums.UserRole;

import java.io.IOException;

public interface UserService {
    User getUserByEmail(String email) throws IOException;
    User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException;
    CardApplication getPendingApplication(User user);
    User addNewUser(String email, String password, UserRole client) throws IOException;
}
