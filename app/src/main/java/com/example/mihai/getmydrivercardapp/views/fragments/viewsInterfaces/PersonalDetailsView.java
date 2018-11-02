package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

public interface PersonalDetailsView extends BaseView,
        DatePickerView, Navigable, CardApplicationTransferable {
    void setCardApplicationFields();
}