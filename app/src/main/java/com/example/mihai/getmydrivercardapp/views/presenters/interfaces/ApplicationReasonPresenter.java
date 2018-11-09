package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface ApplicationReasonPresenter extends BasePresenter{
    void handleOnCheckedChange(int id);
    void handleDialogPositiveButtonOnclick(String reason, CardApplication cardApplication);
}
