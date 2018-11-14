package com.example.mihai.getmydrivercardapp.views.presenters;

import android.view.View;

import com.example.mihai.getmydrivercardapp.constants.Formats;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

public class PersonalDetailsPresenterImpl implements PersonalDetailsPresenter {

    private PersonalDetailsView mPersonalDetailsView;
    private Validator mValidator;
    private SimpleDateFormat df;

    @Inject
    public PersonalDetailsPresenterImpl(){
     //
    }

    @Override
    public void subscribe(PersonalDetailsView view) {
            this.mPersonalDetailsView =  view;
    }

    @Override
    public void handleOnClickNext(CardApplicationReason reason, CardApplication cardApplication)
            throws ParseException {
            setCardApplicationFields(reason, cardApplication);
            mPersonalDetailsView.navigate();
    }

    private void setCardApplicationFields(CardApplicationReason reason, CardApplication cardApplication) {

        try {
            df = new SimpleDateFormat(Formats.STRING_DATE_FORMAT);

            setObligatoryFields(cardApplication);

            if (reason == CardApplicationReason.EXCHANGE) {
                setOptionalExchangeFields(cardApplication);
            } else if (reason == CardApplicationReason.LOST
                    || reason == CardApplicationReason.STOLEN) {
                setOptionalLostFields(cardApplication);
            } else if (reason == CardApplicationReason.EXPIRED
                    || reason == CardApplicationReason.WITHDRAWN) {
                setOptionalExpireFields(cardApplication);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void handlePickDateButtonOnClick() {
        mPersonalDetailsView.showDatePicker();
    }

    @Override
    public void checkReasonAndRevealElementsIfNeeded(CardApplicationReason reason) {

        if (reason == CardApplicationReason.LOST
                ||reason== CardApplicationReason.STOLEN){
        mPersonalDetailsView.showLostOrStolenFields();
        } else if (reason == CardApplicationReason.EXCHANGE){
            mPersonalDetailsView.showExchangeFields();
        } else if (reason == CardApplicationReason.EXPIRED
                || reason==CardApplicationReason.WITHDRAWN){
        mPersonalDetailsView.showRenewalFields();
        }
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
            mPersonalDetailsView.showValidationError(failedRule, view);

        }
    }


    private void setObligatoryFields(CardApplication cardApplication) throws ParseException {
        cardApplication.getDetails()
                .setDriverID(mPersonalDetailsView.getID());
        cardApplication.getDetails()
                .setFirstNameLatin(mPersonalDetailsView.getFirstName());
        cardApplication.getDetails()
                .setSurNameLatin(mPersonalDetailsView.getLastName());
        cardApplication.getDetails()
                .setDriverBirthDate(df.parse(mPersonalDetailsView.getBirthDate()));
    }

    private void setOptionalExchangeFields(CardApplication cardApplication) throws ParseException {
        cardApplication.getDetails()
                .setAuthorityIssuedCard(mPersonalDetailsView.getAuthorityIssuedCard());
        cardApplication.getDetails()
                .setCountryIssuedCard(mPersonalDetailsView.getCountryIssuedCard());
        cardApplication.getDetails()
                .setCardNumber(mPersonalDetailsView.getEuCardNumber());
        cardApplication.getDetails()
                .setDateOfExpiry(df.parse(mPersonalDetailsView
                        .getDateOfExpiry(cardApplication.getCardApplicationReason())));
    }

    private void setOptionalLostFields(CardApplication cardApplication) throws ParseException {
        cardApplication.getDetails()
                .setPlaceOfLoss(mPersonalDetailsView.getPlaceOfLoss());

        cardApplication.getDetails()
                .setDateOfLoss(df.parse(mPersonalDetailsView.getDateOfLoss()));
    }

    private void setOptionalExpireFields(CardApplication cardApplication) throws ParseException {
        cardApplication.getDetails()
                .setDateOfExpiry(df.parse(mPersonalDetailsView
                        .getDateOfExpiry(cardApplication.getCardApplicationReason())));
    }
}
