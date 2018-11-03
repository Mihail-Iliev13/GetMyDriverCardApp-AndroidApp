package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.SearchToolBarView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SearchToolBarPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class SearchToolBarPresenterImpl implements SearchToolBarPresenter {

    private UserService mUserService;
    private AsyncRunner mAsyncRunner;
    private SearchToolBarView mSearchToolBarView;

    @Inject
    public SearchToolBarPresenterImpl(UserService userService, AsyncRunner asyncRunner) {
        this.mUserService = userService;
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
    public void getFilteredCardApplications(String pattern, String filterCriteria){

        mAsyncRunner.runInBackground(() -> {

            try {
                switch (filterCriteria){
                    case "Date":
                        mUserService.filterApplicationsByDate(pattern);
                    case "Name":
                        mUserService.filterApplicationsByName(pattern);
                    case "ID":
                        mUserService.filterApplicationsByID(pattern);
                    case "Status":
                        mUserService.filterApplicationsByStatus(pattern);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setFilterValues() {
        mSearchToolBarView.setSpinnerDropdownList();
    }

    @Override
    public void handleSpinnerOnItemSelected(String item) {

        if (item.equals("Date")) {
            mSearchToolBarView.showDatePicker();
        } else if (item.equals("Status")) {
            mSearchToolBarView.showStatusDialog();
        }
    }
}
