package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.view.View;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;
import com.mobsandgeeks.saripaar.Rule;

public interface PersonalDetailsView extends
        DatePickerView, Navigable, CardApplicationTransferable, ErrorView {
    void showLostOrStolenFields();
    void showExchangeFields();
    void showRenewalFields();
    void setPresenter(PersonalDetailsPresenter presenter);
    String getID();
    String getFirstName();
    String getLastName();
    String getBirthDate();
    String getAuthorityIssuedCard();
    String getCountryIssuedCard();
    String getEuCardNumber();
    String getDateOfExpiry(CardApplicationReason cardApplicationReason);
    String getPlaceOfLoss();
    String getDateOfLoss();

    void showValidationError(Rule failedRule, View view);
}