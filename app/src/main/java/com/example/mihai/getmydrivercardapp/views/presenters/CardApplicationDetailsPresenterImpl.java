package com.example.mihai.getmydrivercardapp.views.presenters;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.utils.bitmapconverter.base.BitmapConverter;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationDetailsPresenter;

import java.security.InvalidParameterException;
import java.util.Date;

import javax.inject.Inject;

public class CardApplicationDetailsPresenterImpl implements CardApplicationDetailsPresenter {

    private CardApplicationDetailsView mCardApplicationDetailsView;
    private BitmapConverter mBitmapConverter;

    @Inject
    public CardApplicationDetailsPresenterImpl(BitmapConverter bitmapConverter) {
        this.mBitmapConverter = bitmapConverter;
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

//        byte[] selfieImage = cardApplication.getDetails().getSelfie();
        Bitmap bitmapSelfie = mBitmapConverter.toBitMap(new byte[2]);
        mCardApplicationDetailsView.assignValueToSelfieImageView(bitmapSelfie);

//        byte[] idCardImage = cardApplication.getDetails().getIdCardImage();
        Bitmap bitmapIDCard = mBitmapConverter.toBitMap(new byte[2]);
        mCardApplicationDetailsView.assignValueToIDCardImageView(bitmapIDCard);

//        byte[] drivingLicenseImage = cardApplication.getDetails().getDrivingLicenseImage();
        Bitmap bitmapDrivingLicense = mBitmapConverter.toBitMap(new byte[2]);
        mCardApplicationDetailsView.assignValueToDrivingLicenseImageView(bitmapDrivingLicense);

        byte[] signatureImage = cardApplication.getDetails().getSignature();
        Bitmap bitmapSignature = mBitmapConverter.toBitMap(signatureImage);
        mCardApplicationDetailsView.assignValueToSignatureImageView(bitmapSignature);

//        byte[] oldCardImage = cardApplication.getDetails().getPreviousCardImage();
//        if (oldCardImage != null){
            Bitmap bitmapOldCard = mBitmapConverter.toBitMap(new byte[2]);
            mCardApplicationDetailsView.assignValueToOldCardImageView(bitmapOldCard);
//        }

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
