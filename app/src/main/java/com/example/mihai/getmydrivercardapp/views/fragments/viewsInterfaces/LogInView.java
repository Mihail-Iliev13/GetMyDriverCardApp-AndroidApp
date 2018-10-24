package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

public interface LogInView extends BaseView, ErrorView {
    void showNoSuchUserToast(String email);
    void showNoMatchingPasswordToast();
    void showCardApplicationStatus();
    void showFillCardApplicationForm();
    void showUserAlreadyExists();
    void showAllPendingApplications();
}
