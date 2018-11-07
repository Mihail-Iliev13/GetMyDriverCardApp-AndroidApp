package com.example.mihai.getmydrivercardapp.views.presenters;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;

import java.security.InvalidParameterException;
import java.text.ParseException;

import javax.inject.Inject;

import butterknife.BindView;

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
    public void handleOnClickNext() throws ParseException {
            mPersonalDetailsView.setCardApplicationFields();

        mPersonalDetailsView.navigate();
    }

    @Override
    public void handleOnClickPickDateButton() {
        (mPersonalDetailsView).showDatePicker();
    }

    @Override
    public void CheckReasonAndRevealElementsIfNeeded(CardApplicationReason reason) {

        if(reason== CardApplicationReason.LOST||reason== CardApplicationReason.STOLEN){
        mPersonalDetailsView.showLostOrStolenFields();
            }
        else if(reason== CardApplicationReason.EXCHANGE){
        mPersonalDetailsView.showExchangeFields();
        }
        else if(reason== CardApplicationReason.EXPIRED || reason==CardApplicationReason.WITHDRAWN){
        mPersonalDetailsView.showRenewalFields();
        }
        else ;
    }


}
