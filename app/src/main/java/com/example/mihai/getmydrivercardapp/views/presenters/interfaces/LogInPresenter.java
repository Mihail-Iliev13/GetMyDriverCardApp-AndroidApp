package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.LogInView;
import com.mobsandgeeks.saripaar.Validator;

import java.io.IOException;

public interface LogInPresenter  {
    void subscribe(LogInView view);

    void logIn(String email, String password) throws IOException;
    void signUp(String email, String password);

    void setValidator (Validator validator);

    void validate();
}
