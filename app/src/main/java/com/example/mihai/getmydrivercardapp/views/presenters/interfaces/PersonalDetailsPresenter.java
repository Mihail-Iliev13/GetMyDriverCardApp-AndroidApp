package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface PersonalDetailsPresenter extends BasePresenter {

    void handleOnClickNext();
    void handleOnClickPickDateButton();
    void CheckReasonAndRevealElementsIfNeeded(CardApplicationReason reason);

}
