package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ContactDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ContactDetailsPresenter;
import com.mobsandgeeks.saripaar.Validator;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class ContactDetailsPresenterImpl implements ContactDetailsPresenter {

    private ContactDetailsView mContactDetailsView;
    private Validator mValidator;

    @Inject
    public ContactDetailsPresenterImpl () {

    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof  ContactDetailsView) {
            this.mContactDetailsView = (ContactDetailsView) view;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void handleOnButtonNextClick() {
        mContactDetailsView.setCardApplicationFields();
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
}
