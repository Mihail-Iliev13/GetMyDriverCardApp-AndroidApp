package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import java.text.ParseException;

public interface PersonalDetailsView extends BaseView,
        DatePickerView, Navigable, CardApplicationTransferable, ErrorView {
    void setCardApplicationFields() throws ParseException;

    void showLostOrStolenFields();

    void showExchangeFields();

    void showRenewalFields();

    void setOptionalExchangeFields();

    void setOptionalLostFields();

    void setOptionalExpireFields();
}