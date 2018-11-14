package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.enums.FilterCriteria;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.SearchToolBarView;

import java.text.ParseException;

public interface SearchToolBarPresenter {
    void subscribe(SearchToolBarView view);

    void setCardApplicationListView(CardApplicationListView cardApplicationListView);

    void getFilteredCardApplications(String pattern, FilterCriteria criteria) throws ParseException;
    void setFilterValues();
    void handleSpinnerOnItemSelected(FilterCriteria filterCriteria);
}
