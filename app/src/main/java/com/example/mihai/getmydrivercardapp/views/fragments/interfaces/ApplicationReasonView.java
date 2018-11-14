package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.app.AlertDialog;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationReasonPresenter;

public interface ApplicationReasonView extends Navigable, CardApplicationTransferable {
    void setCardApplicationReason(CardApplicationReason newCard);
    void showDialog(String title, int resourceID);
    AlertDialog.Builder buildDialog(String title, int resourceID);
    void disableApplicationReasonOptionNew();
    void setPresenter(ApplicationReasonPresenter presenter);

}
