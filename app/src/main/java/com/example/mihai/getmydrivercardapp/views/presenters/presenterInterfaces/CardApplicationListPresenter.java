package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface CardApplicationListPresenter extends BasePresenter {
    void loadCardApplications();

    void selectCardApplication(CardApplication selectedCardApplication);
}
