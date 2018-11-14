package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.content.Intent;

import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.LogInPresenter;

public interface LogInView extends ErrorView {
    void showNoExistingUserError(String email);
    void showNoMatchingPasswordError();
    void showUserAlreadyExistsError(String email);
    void setUser(User user);
    void showPendingApplicationStatus();
    void setNavigator(Navigator navigator);
    Intent prepareIntent();
    void showAllPendingApplications();
    void showApplicationForm();
    User getUser();
    void setPresenter(LogInPresenter presenter);

}
