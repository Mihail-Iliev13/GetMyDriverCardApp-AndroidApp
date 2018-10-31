package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationDetailsPresenter;

import java.security.InvalidParameterException;
import java.util.Date;

import javax.inject.Inject;

public class CardApplicationDetailsPresenterImpl implements CardApplicationDetailsPresenter {

    private CardApplicationDetailsView mCardApplicationDetailsView;

    @Inject
    public CardApplicationDetailsPresenterImpl() {

    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof  CardApplicationDetailsView) {
            this.mCardApplicationDetailsView = (CardApplicationDetailsView) view;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void assignValues(CardApplication cardApplication) {
        String driverID = cardApplication.getDetails().getDriverID();
        mCardApplicationDetailsView.assignValueToIDTextView(driverID);

        String firstName = cardApplication.getDetails().getFirstNameLatin();
        mCardApplicationDetailsView.assignValueToFirstNameTextView(firstName);

        String surName = cardApplication.getDetails().getSurNameLatin();
        mCardApplicationDetailsView.assignValueToSurnameTextView(surName);

        Date date = cardApplication.getDetails().getDriverBirthDate();
        mCardApplicationDetailsView.assignValueToBirthDateTextView(date);

        String address = cardApplication.getDetails().getAddress();
        mCardApplicationDetailsView.assignValueToAddressTextView(address);

        String phoneNumber = cardApplication.getDetails().getPhoneNumber();
        mCardApplicationDetailsView.assignValueToPhoneNumberTextView(phoneNumber);

        String email = cardApplication.getDetails().getEmail();
        mCardApplicationDetailsView.assignValueToEmailTextView(email);

        byte[] selfieImage = cardApplication.getDetails().getSelfie();
        mCardApplicationDetailsView.assignValueToSelfieImageView(selfieImage);

        byte[] idCardImage = cardApplication.getDetails().getIdCardImage();
        mCardApplicationDetailsView.assignValueToIDCardImageView(idCardImage);

        byte[] drivingLicenseImage = cardApplication.getDetails().getDrivingLicenseImage();
        mCardApplicationDetailsView.assignValueToDrivingLicenseImageView(drivingLicenseImage);

        byte[] signatureImage = cardApplication.getDetails().getSignature();
        mCardApplicationDetailsView.assignValueToSignatureImageView(signatureImage);

        byte[] oldCardImage = cardApplication.getDetails().getPreviousCardImage();
        if (oldCardImage != null)
        mCardApplicationDetailsView.assignValueToOldCardImageView(oldCardImage);

        String countryIssuedCard = cardApplication.getDetails().getCountryIssuedCard();
        if (countryIssuedCard != null)
            mCardApplicationDetailsView.assignValueToCountryIssuedCardTextView(countryIssuedCard);


        String authorityIssuedCard = cardApplication.getDetails().getAuthorityIssuedCard();
        if (countryIssuedCard != null)
            mCardApplicationDetailsView.assignValueToAuthorityIssuedCardTextView(authorityIssuedCard);

        String oldCardNumber = cardApplication.getDetails().getCardNumber();
        if (oldCardNumber != null)
            mCardApplicationDetailsView.assignValueToOldCardNumberTextView(oldCardNumber);

        Date dateOfExpiry = cardApplication.getDetails().getDateOfExpiry();
        if (dateOfExpiry != null)
            mCardApplicationDetailsView.assignValueToDateOfExpiryTextView(dateOfExpiry);

        Date dateOfLoss = cardApplication.getDetails().getDateOfLoss();
        if (dateOfLoss != null)
            mCardApplicationDetailsView.assignValueToDateOfLossTextView(dateOfLoss);

        String placeLost = cardApplication.getDetails().getPlaceOfLoss();
        if (placeLost != null) {
            mCardApplicationDetailsView.assignValueToPlaceLostTextView(placeLost);
        }
    }
}
