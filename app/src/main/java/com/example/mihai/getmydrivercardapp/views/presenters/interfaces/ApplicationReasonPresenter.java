package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ApplicationReasonView;

public interface ApplicationReasonPresenter {
    void subscribe(ApplicationReasonView view);

    void handleOnCheckedChange(int id);
    void handleDialogPositiveButtonOnclick(String reason, CardApplication cardApplication);

    void checkUserReason(User user);
}
