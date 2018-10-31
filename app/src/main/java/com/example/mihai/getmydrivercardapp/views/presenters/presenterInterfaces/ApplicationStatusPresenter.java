package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import com.example.mihai.getmydrivercardapp.models.enums.CardAppStatus;

public interface ApplicationStatusPresenter extends BasePresenter {
    void showStatusMessage(CardAppStatus status);
}
