package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import com.example.mihai.getmydrivercardapp.views.FilterCriteria;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationListView;

import java.text.ParseException;

public interface SearchToolBarPresenter extends BasePresenter {
    void setCardApplicationListView(CardApplicationListView cardApplicationListView);

    void getFilteredCardApplications(String pattern, FilterCriteria criteria) throws ParseException;
    void setFilterValues();
    void handleSpinnerOnItemSelected(FilterCriteria filterCriteria);
}
