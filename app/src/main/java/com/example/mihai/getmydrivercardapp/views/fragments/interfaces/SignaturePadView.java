package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.view.View;

import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SignaturePadPresenter;
import com.mobsandgeeks.saripaar.Rule;

public interface SignaturePadView extends ErrorView, CardApplicationTransferable, Navigable {
    void setPresenter(SignaturePadPresenter presenter);
    void showValidationError(View view, Rule failedRule);
}
