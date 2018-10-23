package com.example.mihai.getmydrivercardapp.services;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.repositories.base.Repository;
import com.example.mihai.getmydrivercardapp.services.Base.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class UserService implements Service {
    private Repository mRepository;

    @Inject
    public UserService(Repository mRepository){
        this.mRepository=mRepository;

    }

    @Override
    public User getUserByEmail(String email) throws IOException {
    User user = mRepository.getUserByEmail(email);
    return user;
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        List<User> userList =  mRepository.getAllUsers();
        return userList;

    }

    @Override
    public User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException {
    User user=mRepository.updateUserCardApplication(email,cardApplication);
    return user;

    }

    @Override
    public User addUser(User user) throws IOException {
        return mRepository.addUser(user);
    }
}
