package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

public interface LogInView extends BaseView, ErrorView {
    void showNoSuchUserToast(String email);
    void showNoMatchingPasswordToast();
    void showCardApplicationStatus(CardApplication cardApplication);
    void showFillCardApplicationForm(User user);
    void showUserAlreadyExists();
    void showAllPendingApplications();
}
