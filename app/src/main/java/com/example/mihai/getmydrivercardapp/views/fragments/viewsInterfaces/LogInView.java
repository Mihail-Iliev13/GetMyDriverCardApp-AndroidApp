package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import com.example.mihai.getmydrivercardapp.models.User;

public interface LogInView extends BaseView, ErrorView, Navigable {
    void showNoExistingUserError(String email);
    void showNoMatchingPasswordError();
    void showUserAlreadyExistsError(String email);
    void setUser(User user);
    void showPendingApplicationStatus();

    void showAllPendingApplications();

    void showApplicationForm();

    User getUser();

}
