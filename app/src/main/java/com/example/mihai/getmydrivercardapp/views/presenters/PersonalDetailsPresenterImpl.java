package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.views.activities.ContactDetailsActivity;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.PersonalDetailsPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class PersonalDetailsPresenterImpl implements PersonalDetailsPresenter {
 private PersonalDetailsView mPersonalDetailsView;

 @Inject
 public PersonalDetailsPresenterImpl(){
     //
    }

    @Override
    public void subscribe(BaseView view) {
        if(view instanceof PersonalDetailsView) {
            this.mPersonalDetailsView = (PersonalDetailsView) view;
        }
        else throw new InvalidParameterException();
    }

    @Override
    public void handleOnClickNext() {
        mPersonalDetailsView.setCardApplicationFields();
        mPersonalDetailsView.navigate(ContactDetailsActivity.class);
    }

    @Override
    public void handleOnClickPickDateButton() {
        (mPersonalDetailsView).showDatePicker();
    }
}
