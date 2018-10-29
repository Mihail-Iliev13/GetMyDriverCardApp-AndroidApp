package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import java.text.ParseException;

public interface SearchToolBarPresenter extends BasePresenter {
    void getFilteredCardApplications(String pattern, String criteria) throws ParseException;
    void setFilterValues();
    void handleSpinnerOnItemSelected(String item);
}
