package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import javax.inject.Inject;

public class CardApplicationListPresenterImpl implements CardApplicationListPresenter {

    private UserService mUserService;
    private AsyncRunner mAsyncRunner;
    private CardApplicationListView mCardApplicationListView;

    @Inject
    public CardApplicationListPresenterImpl (UserService userService, AsyncRunner asyncRunner) {
        this.mUserService = userService;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void loadCardApplications() {
        mAsyncRunner.runInBackground(() -> {
            try {
                List<CardApplication> cardApplications = mUserService.getAllCardApplications();
                if (cardApplications.isEmpty()) {
                    mCardApplicationListView.showEmptyListMessage();
                } else {
                    mCardApplicationListView.showApplications(cardApplications);
                }
            } catch (IOException e) {
                mCardApplicationListView.showError(e);
            }
        });
    }

    @Override
    public void selectCardApplication(CardApplication selectedCardApplication) {
        mCardApplicationListView.navigateToCardApplicationDetails(selectedCardApplication);
    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof CardApplicationListView) {
            this.mCardApplicationListView = (CardApplicationListView) view;
        } else {
            throw new InvalidParameterException();
        }
    }
}
