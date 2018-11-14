package com.example.mihai.getmydrivercardapp.views.presenters;

import android.view.View;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ContactDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ContactDetailsPresenter;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import javax.inject.Inject;

public class ContactDetailsPresenterImpl implements ContactDetailsPresenter {

    private ContactDetailsView mContactDetailsView;
    private Validator mValidator;

    @Inject
    public ContactDetailsPresenterImpl () {

    }

    @Override
    public void subscribe(ContactDetailsView view) {
            this.mContactDetailsView = view;
    }

    @Override
    public void handleOnButtonNextClick(CardApplication cardApplication, String address,
                                        String phoneNumber, String email) {
        cardApplication.getDetails().setAddress(address);
        cardApplication.getDetails().setPhoneNumber(phoneNumber);
        cardApplication.getDetails().setEmail(email);
        mContactDetailsView.navigate();
    }

    @Override
    public void validate() {
        mValidator.validate();
    }

    @Override
    public void setValidator(Validator validator) {
        this.mValidator = validator;
    }

    @Override
    public void handleOnValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            Rule failedRule = error.getFailedRules().get(0);
            mContactDetailsView.showValidationError(view, failedRule);
        }
    }
}
