package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.services.Base.Service;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class CardApplicationListPresenterImpl implements CardApplicationListPresenter {

    @Inject
    Service mService;

    @Inject
     AsyncRunner mAsyncRunner;

    public CardApplicationListPresenterImpl (Service service, AsyncRunner asyncRunner) {
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    private CardApplicationListView mCardApplicationListView;


    @Override
    public void loadCardApplications() {
        mAsyncRunner.runInBackground(() -> {
            try {
                List<CardApplication> cardApplications = mService.getAllCardApplications();
                mCardApplicationListView.showApplications(cardApplications);
            } catch (IOException e) {
                mCardApplicationListView.showError(e);
            }
        });
    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof CardApplicationListView) {
            this.mCardApplicationListView = (CardApplicationListView) view;
        }
    }
}
