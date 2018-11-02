package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.models.enums.UserRole;
import com.example.mihai.getmydrivercardapp.services.Base.Service;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.LogInView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.LogInPresenter;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.inject.Inject;

public class LogInPresenterImpl implements LogInPresenter {

    private LogInView mLoginView;
    private Service mService;
    private AsyncRunner mAsyncRunner;

    @Inject
    public LogInPresenterImpl (Service service, AsyncRunner asyncRunner) {
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void subscribe(BaseView view) {

        if (view instanceof LogInView) {
            this.mLoginView = (LogInView) view;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void logIn(String email, String password) {

        mAsyncRunner.runInBackground(() -> {
            try {
                User user = mService.getUserByEmail(email);
                mLoginView.setUser(user);
                if (user == null) {
                    mLoginView.showNoExistingUserError(email);
                } else if (!user.getPassword().equals(password)) {
                    mLoginView.showNoMatchingPasswordError();
                } else if (mService.getPendingApplication(user) != null){
                    mLoginView.showPendingApplicationStatus();
                } else if (user.getUserRole().equals(UserRole.ADMIN)){
                    mLoginView.showAllPendingApplications();
                } else {
                    mLoginView.showApplicationForm();
                }

            } catch (IOException e){
                mLoginView.showError(e);
            }
        });
    }

    @Override
    public void signUp(String email, String password) {
        mAsyncRunner.runInBackground(() -> {

            try {
                User user = mService.getUserByEmail(email);
                if (user == null) {
                    User newUser = mService.addNewUser(email, password, UserRole.CLIENT);
                    mLoginView.setUser(newUser);
                    mLoginView.showApplicationForm();
                } else {
                    mLoginView.showUserAlreadyExistsError(email);
                }
            } catch (IOException e){
                mLoginView.showError(e);
            }
        });
    }
}
