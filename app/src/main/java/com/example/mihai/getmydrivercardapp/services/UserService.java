package com.example.mihai.getmydrivercardapp.services;

import com.example.mihai.getmydrivercardapp.models.enums.CardAppStatus;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.repositories.base.Repository;
import com.example.mihai.getmydrivercardapp.services.Base.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserService implements Service {

    private Repository mRepository;

    @Inject
    public UserService(Repository mRepository){
        this.mRepository = mRepository;
    }

    @Override
    public User getUserByEmail(String email) throws IOException {
        return mRepository.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return mRepository.getAllUsers();

    }

    @Override
    public User updateUserCardApplication(String email, CardApplication cardApplication) throws IOException {
        return mRepository.updateUserCardApplication(email,cardApplication);

    }

    @Override
    public User addUser(User user) throws IOException {
        return mRepository.addUser(user);
    }

    @Override
    public CardApplication getPendingApplication(User user) {
        List<CardApplication> cardApplicationList = user.getCardApplications();

        if (cardApplicationList == null || cardApplicationList.isEmpty()) {
            return null;
        } else {
            for (CardApplication cardApplication: cardApplicationList) {
                if (!cardApplication.getStatus()
                        .equals(CardAppStatus.COMPLETED)) {
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

}
