package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.enums.FilterCriteria;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.CardApplicationListView;

import java.text.ParseException;

public interface SearchToolBarPresenter extends BasePresenter {
    void setCardApplicationListView(CardApplicationListView cardApplicationListView);

    void getFilteredCardApplications(String pattern, FilterCriteria criteria) throws ParseException;
    void setFilterValues();
    void handleSpinnerOnItemSelected(FilterCriteria filterCriteria);
}
