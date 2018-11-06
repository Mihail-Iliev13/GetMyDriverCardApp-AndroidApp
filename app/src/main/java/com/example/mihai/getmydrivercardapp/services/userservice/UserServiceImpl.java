package com.example.mihai.getmydrivercardapp.services.userservice;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.enums.UserRole;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepository;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;

import java.io.IOException;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private UserRepository mUserRepository;

    @Inject
    public UserServiceImpl(UserRepository mUserRepository){
        this.mUserRepository = mUserRepository;
    }

    @Override
    public User getUserByEmail(String email) throws IOException {
        return mUserRepository.getUserByEmail(email);
    }

    @Override
    public User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException {
        return mUserRepository.updateUserCardApplication(email, cardApplication);
    }


    @Override
    public CardApplication getPendingApplication(User user) throws IOException {
       return mUserRepository.getPendingApplication(user);
    }



    @Override
    public User addNewUser(String email, String password, UserRole userRole) throws IOException {
        User newUser = new User(email, password, userRole);
        return mUserRepository.addUser(newUser);
    }

}
