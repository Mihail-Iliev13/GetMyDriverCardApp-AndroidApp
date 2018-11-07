package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;

import java.text.ParseException;

public interface PersonalDetailsPresenter extends BasePresenter {

    void handleOnClickNext() throws ParseException;
    void handleOnClickPickDateButton();
    void CheckReasonAndRevealElementsIfNeeded(CardApplicationReason reason);

}
