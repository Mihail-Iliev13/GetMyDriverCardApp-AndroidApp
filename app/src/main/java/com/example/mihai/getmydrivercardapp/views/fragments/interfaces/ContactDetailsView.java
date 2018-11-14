package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.view.View;

import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ContactDetailsPresenter;
import com.mobsandgeeks.saripaar.Rule;

public interface ContactDetailsView  extends Navigable,
        CardApplicationTransferable {
    void setCardApplicationFields();
    void setPresenter(ContactDetailsPresenter presenter);

    void showValidationError(View view, Rule failedRule);
}
