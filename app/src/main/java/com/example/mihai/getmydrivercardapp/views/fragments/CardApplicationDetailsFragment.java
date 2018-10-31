package com.example.mihai.getmydrivercardapp.views.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationDetailsPresenter;

import java.security.InvalidParameterException;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardApplicationDetailsFragment extends Fragment implements CardApplicationDetailsView {

    @BindView(R.id.tv_id) TextView mID;
    @BindView(R.id.tv_first_name) TextView mFirstName;
    @BindView(R.id.tv_surname) TextView mSurName;
    @BindView(R.id.tv_birth_date) TextView mBirthDate;
    @BindView(R.id.tv_address) TextView mAddress;
    @BindView(R.id.tv_phone_number) TextView mPhoneNumber;
    @BindView(R.id.tv_email) TextView mEmail;
    @BindView(R.id.iv_selfie) ImageView mSelfieImage;
    @BindView(R.id.iv_id_card_image) ImageView mIDCardImage;
    @BindView(R.id.iv_driving_license_image) ImageView mDrivingLicenseImage;
    @BindView(R.id.iv_signature_image) ImageView mSignatureImage;
    @BindView(R.id.iv_old_card_image) ImageView mOldCardImage;
    @BindView(R.id.tv_country_issued_card) TextView mCountryIssuedCard;
    @BindView(R.id.tv_authority_issued_card) TextView mAuthorityIssuedCard;
    @BindView(R.id.tv_old_card_number) TextView mOldCardNumber;
    @BindView(R.id.tv_date_of_expiry) TextView mDateOfExpiry;
    @BindView(R.id.tv_date_of_loss) TextView mDateOfLoss;
    @BindView(R.id.tv_place_lost) TextView mPlaceLost;
    @BindView(R.id.ll_old_card_image) LinearLayout mOldCardImageLayout;
    @BindView(R.id.ll_country_issued_card) LinearLayout mCountryIssuedCardLayout;
    @BindView(R.id.ll_authority_issued_card) LinearLayout mAuthorityIssuedCardLayout;
    @BindView(R.id.ll_old_card_number) LinearLayout mOldCardNumberLayout;
    @BindView(R.id.ll_date_of_expiry) LinearLayout mDateOfExpiryLayout;
    @BindView(R.id.ll_date_lost) LinearLayout mDateLostLayout;
    @BindView(R.id.ll_place_of_loss) LinearLayout mPlaceLostLayout;

    private CardApplicationDetailsPresenter mCardApplicationDetailsPresenter;
    private CardApplication mCardApplication;

    @Inject
    public CardApplicationDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_application_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCardApplicationDetailsPresenter.assignValues(mCardApplication);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof CardApplicationDetailsPresenter) {
            this.mCardApplicationDetailsPresenter = (CardApplicationDetailsPresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void setCardApplication(CardApplication cardApplication) {
        this.mCardApplication = cardApplication;
    }

    @Override
    public void assignValueToIDTextView(String driverID) {
        mID.setText(driverID);
    }

    @Override
    public void assignValueToFirstNameTextView(String firstName) {
        mFirstName.setText(firstName);
    }

    @Override
    public void assignValueToSurnameTextView(String surName) {
        mSurName.setText(surName);
    }

    @Override
    public void assignValueToBirthDateTextView(Date date) {
        mBirthDate.setText(String.valueOf(date));
    }

    @Override
    public void assignValueToDrivingLicenseImageView(byte[] drivingLicenseImage) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(drivingLicenseImage, 0, drivingLicenseImage.length);
        mDrivingLicenseImage.setImageBitmap(bitmap);
    }

    @Override
    public void assignValueToAddressTextView(String address) {
        mAddress.setText(address);
    }

    @Override
    public void assignValueToPhoneNumberTextView(String phoneNumber) {
        mPhoneNumber.setText(phoneNumber);
    }

    @Override
    public void assignValueToEmailTextView(String email) {
        mEmail.setText(email);
    }

    @Override
    public void assignValueToSelfieImageView(byte[] selfieImage) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(selfieImage, 0, selfieImage.length);
        mSelfieImage.setImageBitmap(bitmap);
    }

    @Override
    public void assignValueToIDCardImageView(byte[] idCardImage) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(idCardImage, 0, idCardImage.length);
        mIDCardImage.setImageBitmap(bitmap);
    }

    @Override
    public void assignValueToSignatureImageView(byte[] signatureImage) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(signatureImage, 0, signatureImage.length);
        mSignatureImage.setImageBitmap(bitmap);
    }

    @Override
    public void assignValueToOldCardImageView(byte[] oldCardImage) {
        mOldCardImageLayout.setVisibility(View.VISIBLE);
        Bitmap bitmap = BitmapFactory.decodeByteArray(oldCardImage, 0, oldCardImage.length);
        mOldCardImage.setImageBitmap(bitmap);
    }

    @Override
    public void assignValueToCountryIssuedCardTextView(String countryIssuedCard) {
        mCountryIssuedCardLayout.setVisibility(View.VISIBLE);
        mCountryIssuedCard.setText(countryIssuedCard);
    }

    @Override
    public void assignValueToAuthorityIssuedCardTextView(String authorityIssuedCard) {
        mAuthorityIssuedCardLayout.setVisibility(View.VISIBLE);
        mAuthorityIssuedCard.setText(authorityIssuedCard);
    }

    @Override
    public void assignValueToOldCardNumberTextView(String oldCardNumber) {
        mOldCardNumberLayout.setVisibility(View.VISIBLE);
        mOldCardNumber.setText(oldCardNumber);
    }

    @Override
    public void assignValueToDateOfExpiryTextView(Date dateOfExpiry) {
        mDateOfExpiryLayout.setVisibility(View.VISIBLE);
        mDateOfExpiry.setText(String.valueOf(dateOfExpiry));
    }

    @Override
    public void assignValueToDateOfLossTextView(Date dateOfLoss) {
        mDateLostLayout.setVisibility(View.VISIBLE);
        mDateOfLoss.setText(String.valueOf(dateOfLoss));
    }

    @Override
    public void assignValueToPlaceLostTextView(String placeLost) {
        mPlaceLostLayout.setVisibility(View.VISIBLE);
        mPlaceLost.setText(placeLost);
    }
}
