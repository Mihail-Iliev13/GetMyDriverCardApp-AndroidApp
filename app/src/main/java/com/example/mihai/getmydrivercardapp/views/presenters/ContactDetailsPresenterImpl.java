package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ContactDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ContactDetailsPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class ContactDetailsPresenterImpl implements ContactDetailsPresenter{
 private ContactDetailsView mContactDetailsView;

    @Inject
    public ContactDetailsPresenterImpl(){
        //
    }

    @Override
    public void handleOnClickNext() {

        mContactDetailsView.assignValues();
        mContactDetailsView.navigateToNextView();
    }

    @Override
    public void subscribe(BaseView view) {
        if(view instanceof ContactDetailsView) {
            this.mContactDetailsView = (ContactDetailsView) view;
        }
        else throw new InvalidParameterException();
    }
}
