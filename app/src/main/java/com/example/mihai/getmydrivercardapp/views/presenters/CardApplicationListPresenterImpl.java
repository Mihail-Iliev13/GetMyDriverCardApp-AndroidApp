package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.CardApplicationListPresenter;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import javax.inject.Inject;

public class CardApplicationListPresenterImpl implements CardApplicationListPresenter {

    private CardApplicationService mCardApplicationService;
    private AsyncRunner mAsyncRunner;
    private CardApplicationListView mCardApplicationListView;


    @Inject
    public CardApplicationListPresenterImpl (CardApplicationService cardApplicationService,
                                             AsyncRunner asyncRunner) {
        this.mCardApplicationService = cardApplicationService;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void loadCardApplications() {
        mCardApplicationListView.showLoading();
        mAsyncRunner.runInBackground(() -> {
            try {
                List<CardApplication> cardApplications = mCardApplicationService.getAllApplications();

                if (cardApplications.isEmpty()) {
                    mCardApplicationListView.showEmptyListMessage();
                } else {
                    mCardApplicationListView.showApplications(cardApplications);
                }
            } catch (Exception e) {
                mCardApplicationListView.showError(e);
            }
            mCardApplicationListView.hideLoading();
        });
    }

    @Override
    public void selectCardApplication(CardApplication selectedCardApplication) {
        mCardApplicationListView.navigateToCardApplicationDetails(selectedCardApplication);
    }

    @Override
    public void updateApplicationStatus(CardApplication cardApplication, String status) {

       mAsyncRunner.runInBackground(() -> {
           try {
               mCardApplicationService
                       .updateCardApplicationStatus(cardApplication, status);
               loadCardApplications();
           } catch (IOException e) {
               mCardApplicationListView.showError(e);
           }
       });

    }

    @Override
    public void handleChangeStatusOnClick() {
        mCardApplicationListView.showStatusDialog();
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
