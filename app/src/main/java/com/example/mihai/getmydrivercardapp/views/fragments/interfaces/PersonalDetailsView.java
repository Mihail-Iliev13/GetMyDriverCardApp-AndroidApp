package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

public interface PersonalDetailsView extends BaseView,
        DatePickerView, Navigable, CardApplicationTransferable {
    void setCardApplicationFields();
}