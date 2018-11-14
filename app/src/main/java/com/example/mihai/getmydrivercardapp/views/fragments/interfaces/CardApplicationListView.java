package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.app.AlertDialog;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.CardApplicationListPresenter;

import java.util.List;

public interface CardApplicationListView extends ErrorView, Loadable {
    void showApplications(List<CardApplication> cardApplications);
    void showEmptyListMessage();
    void navigateToCardApplicationDetails(CardApplication cardApplication);
    AlertDialog.Builder buildStatusDialog();
    void showStatusDialog();
    void setSelectedCardApplication(CardApplication selectedCardApplication);
    void setPresenter(CardApplicationListPresenter presenter);

}
