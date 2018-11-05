package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.views.FilterCriteria;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.SearchToolBarView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SearchToolBarPresenter;

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
                List<CardApplication> filteredApplications = null;
                switch (filterCriteria){
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
                }

                if (filteredApplications != null) {
                    mCardApplicationsListView.showApplications(filteredApplications);
                } else {
                    mCardApplicationsListView.showEmptyListMessage();
                }
            } catch (Exception e){
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
        }
    }
}
