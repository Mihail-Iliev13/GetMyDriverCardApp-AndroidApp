package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import java.io.IOException;

public interface LogInPresenter extends BasePresenter {
    void logIn(String email, String password) throws IOException;
    void signUp(String email, String password);
}
