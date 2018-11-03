package com.example.mihai.getmydrivercardapp.services.userservice;

import android.annotation.SuppressLint;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.models.enums.UserRole;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepository;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<User> getAllUsers() throws IOException {
        return mUserRepository.getAllUsers();

    }

    @Override
    public User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException {
        return mUserRepository.updateUserCardApplication(email, cardApplication);
    }


    @Override
    public CardApplication getPendingApplication(User user) {
        List<CardApplication> cardApplicationList = user.getCardApplications();

        if (cardApplicationList == null || cardApplicationList.isEmpty()) {
            return null;
        } else {
            for (CardApplication cardApplication: cardApplicationList) {
                if (!cardApplication.getStatus()
                        .equals(CardApplicationStatus.COMPLETED)) {
                    return cardApplication;
                }
            }
            return null;
        }
    }

    @Override
    public List<CardApplication> getAllCardApplications() throws IOException {
        List<CardApplication> cardApplications = new ArrayList<>();
        List<User> users = getAllUsers();
        for (User user : users) {
            cardApplications.addAll(user.getCardApplications());
        }
        return cardApplications;
    }

    @Override
    public void filterApplicationsByName(String pattern) {

    }

    @Override
    public void filterApplicationsByID(String id) {

    }

    @Override
    public void filterApplicationsByDate(String dateString) throws ParseException {
        @SuppressLint("SimpleDateFormat")
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }

    @Override
    public void filterApplicationsByStatus(String pattern) {

    }

    @Override
    public User addNewUser(String email, String password, UserRole userRole) throws IOException {
        User newUser = new User(email, password, userRole);
        return mUserRepository.addUser(newUser);
    }

}
