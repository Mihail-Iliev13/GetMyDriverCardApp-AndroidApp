package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface CardApplicationListPresenter extends BasePresenter {
    void loadCardApplications();

    void selectCardApplication(CardApplication selectedCardApplication);

    void updateApplicationStatus(CardApplication cardApplication, String status);

    void handleChangeStatusOnClick();

}
