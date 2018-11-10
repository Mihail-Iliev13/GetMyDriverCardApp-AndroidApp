package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.enums.FilterCriteria;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.SearchToolBarView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SearchToolBarPresenter;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import javax.inject.Inject;

public class SearchToolBarPresenterImpl implements SearchToolBarPresenter {

    private CardApplicationService mCardApplicationService;
    private AsyncRunner mAsyncRunner;
    private SearchToolBarView mSearchToolBarView;
    private CardApplicationListView mCardApplicationsListView;

    @Inject
    public SearchToolBarPresenterImpl(CardApplicationService cardApplicationService, AsyncRunner asyncRunner) {
        this.mCardApplicationService = cardApplicationService;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof SearchToolBarView) {
            this.mSearchToolBarView = (SearchToolBarView) view;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void setCardApplicationListView(CardApplicationListView cardApplicationListView) {
        this.mCardApplicationsListView = cardApplicationListView;
    }

    @Override
    public void getFilteredCardApplications(String pattern, FilterCriteria filterCriteria){

            mAsyncRunner.runInBackground(() -> {
                try {
                    if (pattern.length() == 0) {
                        mCardApplicationsListView
                                .showApplications(mCardApplicationService.getAllApplications());
                        return;
                    }

                    List<CardApplication> filteredApplications = null;
                    switch (filterCriteria) {
                        case DATE_OF_SUBMISSION:
                            filteredApplications = mCardApplicationService
                                    .filterApplicationsByDate(pattern);
                            break;
                        case NAME:
                            filteredApplications = mCardApplicationService
                                    .filterApplicationsByName(pattern);
                            break;
                        case ID:
                            filteredApplications = mCardApplicationService
                                    .filterApplicationsByID(pattern);
                            break;
                        case STATUS:
                            filteredApplications = mCardApplicationService
                                    .filterApplicationsByStatus(pattern);
                            break;
                        case SHOW_ALL:
                         filteredApplications = mCardApplicationService.getAllApplications();
                            break;
                    }

                        mCardApplicationsListView.showApplications(filteredApplications);

                    if (filteredApplications.isEmpty()) {
                        mCardApplicationsListView.showEmptyListMessage();
                    }
                } catch (Exception e) {
                    mCardApplicationsListView.showError(e);
                }
            });
        }

    @Override
    public void setFilterValues() {
        mSearchToolBarView.setSpinnerDropdownList();
    }

    @Override
    public void handleSpinnerOnItemSelected(FilterCriteria filterCriteria) {

        if (filterCriteria.equals(FilterCriteria.DATE_OF_SUBMISSION)) {
            mSearchToolBarView.showDatePicker();
        } else if (filterCriteria.equals(FilterCriteria.STATUS)) {
            mSearchToolBarView.showStatusDialog();
        } else if (filterCriteria.equals(FilterCriteria.SHOW_ALL)) {
            mAsyncRunner.runInBackground(() -> {
                try {
                    mCardApplicationsListView.showApplications(mCardApplicationService
                            .getAllApplications());
                } catch (IOException e) {
                    mCardApplicationsListView.showError(e);
                }
            });
            mSearchToolBarView.setSpinnerSelectedItemToDefaultValue();
        }
    }
}
