package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.utils.statusconverter.base.ApplicationStatusConverter;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;

import java.security.InvalidParameterException;
import java.util.List;

import javax.inject.Inject;

public class CardApplicationListPresenterImpl implements CardApplicationListPresenter {

    private CardApplicationService mCardApplicationService;
    private AsyncRunner mAsyncRunner;
    private CardApplicationListView mCardApplicationListView;
    private ApplicationStatusConverter mApplicationStatusConverter;

    @Inject
    public CardApplicationListPresenterImpl (CardApplicationService cardApplicationService,
                                             AsyncRunner asyncRunner,
                                             ApplicationStatusConverter applicationStatusConverter) {
        this.mCardApplicationService = cardApplicationService;
        this.mAsyncRunner = asyncRunner;
        this.mApplicationStatusConverter = applicationStatusConverter;
    }

    @Override
    public void loadCardApplications() {
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
        });
    }

    @Override
    public void selectCardApplication(CardApplication selectedCardApplication) {
        mCardApplicationListView.navigateToCardApplicationDetails(selectedCardApplication);
    }

    @Override
    public void updateApplicationStatus(String statusString) {
       CardApplicationStatus status =  mApplicationStatusConverter.fromString(statusString);
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
