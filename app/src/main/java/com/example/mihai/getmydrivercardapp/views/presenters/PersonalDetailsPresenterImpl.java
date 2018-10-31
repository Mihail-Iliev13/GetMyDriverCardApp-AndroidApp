package com.example.mihai.getmydrivercardapp.views.presenters;

import android.view.View;

import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.PersonalDetailsPresenter;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;

import javax.inject.Inject;

public class PersonalDetailsPresenterImpl implements PersonalDetailsPresenter {
 private PersonalDetailsView mPersDetailsView;

 @Inject
 public PersonalDetailsPresenterImpl(){
     //
    }

    @Override
    public void subscribe(BaseView view) {
        if(view instanceof PersonalDetailsView) {
            this.mPersDetailsView = (PersonalDetailsView) view;
        }
        else throw new InvalidParameterException();
    }

    @Override
    public void handleOnClickNext() {
        mPersDetailsView.assignValues();
        mPersDetailsView.navigateToNextView();
    }
}
