package com.example.mihai.getmydrivercardapp.services.userservice;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.models.enums.UserRole;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepository;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;

import java.io.IOException;
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
    public User addNewUser(String email, String password, UserRole userRole) throws IOException {
        User newUser = new User(email, password, userRole);
        return mUserRepository.addUser(newUser);
    }

}
