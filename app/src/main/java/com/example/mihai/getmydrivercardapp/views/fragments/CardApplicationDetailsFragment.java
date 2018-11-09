package com.example.mihai.getmydrivercardapp.views.fragments;


import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.CardApplicationDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.CardApplicationDetailsPresenter;
import com.github.chrisbanes.photoview.PhotoView;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Objects;

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
    @BindView(R.id.btn_change_status) Button mChangeStatusButton;
    @BindView(R.id.tv_status) TextView mStatus;
    @BindView(R.id.tv_reason) TextView mReason;

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

        View.OnClickListener imageOnCLicListener = imageView -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
            View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageDrawable(((ImageView)imageView).getDrawable());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        };

        mSelfieImage.setOnClickListener(imageOnCLicListener);
        mIDCardImage.setOnClickListener(imageOnCLicListener);
        mDrivingLicenseImage.setOnClickListener(imageOnCLicListener);
        mSignatureImage.setOnClickListener(imageOnCLicListener);
        mOldCardImage.setOnClickListener(imageOnCLicListener);
        mChangeStatusButton.setOnClickListener(v -> showStatusDialog());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCardApplicationDetailsPresenter.loadImages(mCardApplication.getId());
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
        this.mID.setText(driverID);
    }

    @Override
    public void assignValueToFirstNameTextView(String firstName) {
        this.mFirstName.setText(firstName);
    }

    @Override
    public void assignValueToSurnameTextView(String surName) {
        this.mSurName.setText(surName);
    }

    @Override
    public void assignValueToBirthDateTextView(String date) {
        this.mBirthDate.setText(String.valueOf(date));
    }

    @Override
    public void assignValueToDrivingLicenseImageView(Bitmap drivingLicenseImage) {
        this.mDrivingLicenseImage.setImageBitmap(drivingLicenseImage);
    }

    @Override
    public void assignValueToAddressTextView(String address) {
        this.mAddress.setText(address);
    }

    @Override
    public void assignValueToPhoneNumberTextView(String phoneNumber) {
        this.mPhoneNumber.setText(phoneNumber);
    }

    @Override
    public void assignValueToEmailTextView(String email) {
        this.mEmail.setText(email);
    }

    @Override
    public void assignValueToSelfieImageView(Bitmap selfieImage) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            this.mSelfieImage.setImageBitmap(selfieImage);
        });
    }

    @Override
    public void assignValueToIDCardImageView(Bitmap idCardImage) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            this.mIDCardImage.setImageBitmap(idCardImage);
        });
    }

    @Override
    public void assignValueToSignatureImageView(Bitmap signatureImage) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            this.mSignatureImage.setImageBitmap(signatureImage);
        });
    }

    @Override
    public void assignValueToOldCardImageView(Bitmap oldCardImage) {
        this.mOldCardImageLayout.setVisibility(View.VISIBLE);
        this.mOldCardImage.setImageBitmap(oldCardImage);
    }

    @Override
    public void assignValueToCountryIssuedCardTextView(String countryIssuedCard) {
        this.mCountryIssuedCardLayout.setVisibility(View.VISIBLE);
        this.mCountryIssuedCard.setText(countryIssuedCard);
    }

    @Override
    public void assignValueToAuthorityIssuedCardTextView(String authorityIssuedCard) {
        this.mAuthorityIssuedCardLayout.setVisibility(View.VISIBLE);
        this.mAuthorityIssuedCard.setText(authorityIssuedCard);
    }

    @Override
    public void assignValueToOldCardNumberTextView(String oldCardNumber) {
        this.mOldCardNumberLayout.setVisibility(View.VISIBLE);
        this.mOldCardNumber.setText(oldCardNumber);
    }

    @Override
    public void assignValueToDateOfExpiryTextView(Date dateOfExpiry) {
        this.mDateOfExpiryLayout.setVisibility(View.VISIBLE);
        this.mDateOfExpiry.setText(String.valueOf(dateOfExpiry));
    }

    @Override
    public void assignValueToDateOfLossTextView(Date dateOfLoss) {
        this.mDateLostLayout.setVisibility(View.VISIBLE);
        this.mDateOfLoss.setText(String.valueOf(dateOfLoss));
    }

    @Override
    public void assignValueToPlaceLostTextView(String placeLost) {
        this.mPlaceLostLayout.setVisibility(View.VISIBLE);
        this.mPlaceLost.setText(placeLost);
    }

    @Override
    public AlertDialog.Builder buildStatusDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Change status: ");
        String[] statusValues = CardApplicationStatus.stringValues();

        final int[] statusIndex = {0};
        builder.setSingleChoiceItems(statusValues, -1, (dialog, index) -> {
            statusIndex[0] = index;
        });

        builder.setPositiveButton("OK", (dialog, which) -> {
            String status = statusValues[statusIndex[0]];
            mCardApplicationDetailsPresenter.updateApplicationStatus(mCardApplication, status);
            mStatus.setText(status);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {

        });

        return builder;
    }

    @Override
    public void showStatusDialog() {
        AlertDialog.Builder builder = buildStatusDialog();
        AlertDialog alertDialog = builder.create();

        Objects.requireNonNull(getActivity())
                .runOnUiThread(alertDialog::show);
    }

    @Override
    public void assignValueToStatusTextView(String status) {
        mStatus.setText(status);
    }

    @Override
    public void assignValueToReasonTextView(String reason) {
        mReason.setText(reason);
    }
}
