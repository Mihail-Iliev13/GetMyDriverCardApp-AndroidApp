package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.views.activities.SelfieCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ContactDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ContactDetailsPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class ContactDetailsPresenterImpl implements ContactDetailsPresenter {

    private ContactDetailsView mContactDetailsView;

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
        mContactDetailsView.navigate(SelfieCaptureActivity.class);
    }
}
