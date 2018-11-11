package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.app.AlertDialog;
import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface CardApplicationDetailsView extends BaseView, Loadable{
    void setCardApplication(CardApplication mCardApplication);
    void assignValueToIDTextView(String driverID);
    void assignValueToFirstNameTextView(String firstName);
    void assignValueToSurnameTextView(String surName);
    void assignValueToBirthDateTextView(String date);
    void assignValueToDrivingLicenseImageView(Bitmap drivingLicenseImage);
    void assignValueToAddressTextView(String address);
    void assignValueToPhoneNumberTextView(String phoneNumber);
    void assignValueToEmailTextView(String email);
    void assignValueToSelfieImageView(Bitmap selfieImage);
    void assignValueToIDCardImageView(Bitmap idCardImage);
    void assignValueToSignatureImageView(Bitmap signatureImage);
    void assignValueToCountryIssuedCardTextView(String countryIssuedCard);
    void assignValueToAuthorityIssuedCardTextView(String authorityIssuedCard);
    void assignValueToOldCardNumberTextView(String oldCardNumber);
    void assignValueToDateOfExpiryTextView(String dateOfExpiry);
    void assignValueToDateOfLossTextView(String dateOfLoss);
    void assignValueToPlaceLostTextView(String placeLost);
    void assignValueToOldCardImageView(Bitmap oldCardImage);

    AlertDialog.Builder buildStatusDialog();

    void showStatusDialog();

    void assignValueToStatusTextView(String string);

    void assignValueToReasonTextView(String string);
}
