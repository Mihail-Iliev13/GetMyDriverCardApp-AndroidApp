package com.example.mihai.getmydrivercardapp.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    public User() {
        //required empty constructor
    }
    public User (String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }


    private String email;
    private String password;
    private UserRole userRole;
    private List<CardApplication> cardApplications;

    public CardApplication getPendingCardApplication() {
        return null;
    }

    public void addCardApplication(CardApplication newCardApplication) {
        cardApplications.add(newCardApplication);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public List<CardApplication> getCardApplications() {
        return new ArrayList<>(cardApplications);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setCardApplications(List<CardApplication> cardApplications) {
        this.cardApplications = cardApplications;
    }
}
