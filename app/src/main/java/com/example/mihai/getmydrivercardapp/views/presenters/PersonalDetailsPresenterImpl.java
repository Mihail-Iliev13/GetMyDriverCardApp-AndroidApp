package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;
import com.mobsandgeeks.saripaar.Validator;

import java.security.InvalidParameterException;
import java.text.ParseException;

import javax.inject.Inject;

public class PersonalDetailsPresenterImpl implements PersonalDetailsPresenter {

    private PersonalDetailsView mPersonalDetailsView;
    private Validator mValidator;

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
    public void checkReasonAndRevealElementsIfNeeded(CardApplicationReason reason) {

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

    @Override
    public void validate() {
        mValidator.validate();
    }

    @Override
    public void setValidator(Validator validator) {
        this.mValidator = validator;
    }


}
