package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.models.User;

public interface ApplicationStatusPresenter extends BasePresenter {
    void loadStatusMessage(User user);
}
