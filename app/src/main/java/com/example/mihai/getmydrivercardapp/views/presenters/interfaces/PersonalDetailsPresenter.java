package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.mobsandgeeks.saripaar.Validator;

import java.text.ParseException;

public interface PersonalDetailsPresenter extends BasePresenter {

    void handleOnClickNext() throws ParseException;
    void handleOnClickPickDateButton();
    void checkReasonAndRevealElementsIfNeeded(CardApplicationReason reason);
    void validate();
    void setValidator(Validator validator);
}
