package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

import java.util.Date;

public interface CardApplicationDetailsView extends BaseView{
    void setCardApplication(CardApplication mCardApplication);
    void assignValueToIDTextView(String driverID);
    void assignValueToFirstNameTextView(String firstName);
    void assignValueToSurnameTextView(String surName);
    void assignValueToBirthDateTextView(Date date);
    void assignValueToDrivingLicenseImageView(byte[] drivingLicenseImage);
    void assignValueToAddressTextView(String address);
    void assignValueToPhoneNumberTextView(String phoneNumber);
    void assignValueToEmailTextView(String email);
    void assignValueToSelfieImageView(byte[] selfieImage);
    void assignValueToIDCardImageView(byte[] idCardImage);
    void assignValueToSignatureImageView(byte[] signatureImage);
    void assignValueToCountryIssuedCardTextView(String countryIssuedCard);
    void assignValueToAuthorityIssuedCardTextView(String authorityIssuedCard);
    void assignValueToOldCardNumberTextView(String oldCardNumber);
    void assignValueToDateOfExpiryTextView(Date dateOfExpiry);
    void assignValueToDateOfLossTextView(Date dateOfLoss);
    void assignValueToPlaceLostTextView(String placeLost);
    void assignValueToOldCardImageView(byte[] oldCardImage);
}