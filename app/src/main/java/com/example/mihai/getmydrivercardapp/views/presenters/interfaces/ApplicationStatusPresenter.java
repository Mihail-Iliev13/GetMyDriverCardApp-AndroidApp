package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ApplicationStatusView;

public interface ApplicationStatusPresenter  {
    void subscribe(ApplicationStatusView view);

    void loadStatusMessage(User user);

    void logOut();
}
