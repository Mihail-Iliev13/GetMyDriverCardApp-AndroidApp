package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;

public interface ApplicationStatusPresenter extends BasePresenter {
    void showStatusMessage(CardApplicationStatus status);
}
