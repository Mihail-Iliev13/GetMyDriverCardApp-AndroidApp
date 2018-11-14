package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.CardApplicationListView;

public interface CardApplicationListPresenter  {
    void loadCardApplications();

    void selectCardApplication(CardApplication selectedCardApplication);

    void updateApplicationStatus(CardApplication cardApplication, String status);

    void handleChangeStatusOnClick();

    void subscribe(CardApplicationListView view);
}
