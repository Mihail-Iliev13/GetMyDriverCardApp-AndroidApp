package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

public interface ApplicationReasonPresenter extends BasePresenter{
    void handleOnCheckedChange(int id);
    void handleDialogPositiveButtonOnclick(String reason, CardApplication cardApplication);

    void checkUserReason(User user);
}
