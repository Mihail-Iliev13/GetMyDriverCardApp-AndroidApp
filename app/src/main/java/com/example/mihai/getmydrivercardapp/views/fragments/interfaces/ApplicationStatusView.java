package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationStatusPresenter;

public interface ApplicationStatusView extends Loadable, Navigable {
    void showStatus(String message, int drawable);
    void setUser(User user);
    void setPresenter(ApplicationStatusPresenter presenter);
}
