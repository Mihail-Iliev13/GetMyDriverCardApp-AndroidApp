package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import android.app.AlertDialog;

import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationReason;

public interface ApplicationReasonView extends BaseView, Navigable, CardApplicationTransferable {
    void setCardApplicationReason(CardApplicationReason newCard);
    void showDialog(String title, int resourceID);
    AlertDialog.Builder buildDialog(String title, int resourceID);
}
