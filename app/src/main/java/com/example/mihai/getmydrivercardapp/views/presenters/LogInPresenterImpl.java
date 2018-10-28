package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.models.UserRole;
import com.example.mihai.getmydrivercardapp.services.Base.Service;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.LogInView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.LogInPresenter;

import java.io.IOException;

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
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void logIn(String email, String password) {

        mAsyncRunner.runInBackground(() -> {

            try {
                User user = mService.getUserByEmail(email);
                if (user == null) {
                    mLoginView.showNoSuchUserToast(email);
                } else if (!user.getPassword().equals(password)) {
                    mLoginView.showNoMatchingPasswordToast();
                } else if (mService.getPendingApplication(user) != null){
                    CardApplication cardApplication = mService.getPendingApplication(user);
                    mLoginView.showCardApplicationStatus(cardApplication);
                } else if (user.getUserRole().equals(UserRole.ADMIN)){
                    mLoginView.showAllPendingApplications();
                } else {
                    mLoginView.showFillCardApplicationForm(user);
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
                    User newUser = new User(email, password, UserRole.CLIENT);
                    mService.addUser(newUser);
                    mLoginView.showFillCardApplicationForm(user);
                } else {
                    mLoginView.showUserAlreadyExists();
                }

            } catch (IOException e){
                mLoginView.showError(e);
            }
        });
    }
}
