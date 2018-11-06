package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;

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
        mPersonalDetailsView.navigate();
    }

    @Override
    public void handleOnClickPickDateButton() {
        (mPersonalDetailsView).showDatePicker();
    }
}
