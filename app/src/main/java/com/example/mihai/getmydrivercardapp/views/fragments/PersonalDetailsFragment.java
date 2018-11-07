package com.example.mihai.getmydrivercardapp.views.fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalDetailsFragment extends Fragment implements PersonalDetailsView,
        DatePickerDialog.OnDateSetListener {

    @BindView(R.id.et_user_id) EditText mUserIdET;
    @BindView(R.id.et_first_name) EditText mFirstNameET;
    @BindView(R.id.et_last_name) EditText mLastNameET;
    @BindView(R.id.btn_next) Button mButtonNext;
    @BindView(R.id.btn_pick_date) Button mPickDateButton;
    @BindView(R.id.tv_birth_date_preview) TextView mBirthDatePreview;

    @BindView(R.id.tv_lost_date_preview) TextView mLostDatePreview;
    @BindView(R.id.btn_lost_date) Button mPickLostDateButton;
    @BindView(R.id.et_place_lost) EditText mPlaceLostET;
    @BindView(R.id.et_country) EditText mCountryIssuedCardET;
    @BindView(R.id.et_authority) EditText mAuthorityIssuedET;
    @BindView(R.id.et_old_card_number) EditText mEUcardNumberET;
    @BindView(R.id.btn_expiry_date_old) Button mPickDateOfExpiryEUcardButton;
    @BindView(R.id.tv_date_of_expiry_old) TextView mDateOfExpiryEUcardPreview;
    @BindView(R.id.btn_expiry_date_renewal) Button mPickExpiryDate_RenewalButton;
    @BindView(R.id.tv_date_of_expiry_renewal) TextView mExpiryDate_RenewalPreview;

    @BindView(R.id.rl_reason_lost)
    RelativeLayout mLostElements;
    @BindView(R.id.rl_reason_exchange)
    RelativeLayout mExchangeElements;
    @BindView(R.id.rl_reason_renewal)
    RelativeLayout mRenewalElements;

    private PersonalDetailsPresenter mPersonalDetailsPresenter;
    private CardApplication mCardApplication;
    private User mUser;
    private Navigator mNavigator;

    @Inject
    public PersonalDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_details, container, false);
        ButterKnife.bind(this, view);

        mPersonalDetailsPresenter.CheckReasonAndRevealElementsIfNeeded(mCardApplication.getCardApplicationReason());

        mButtonNext.setOnClickListener(v -> {
            try {
                mPersonalDetailsPresenter.handleOnClickNext();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        mPickDateButton.setOnClickListener(v -> mPersonalDetailsPresenter.handleOnClickPickDateButton());
        return view;
    }


    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof  PersonalDetailsPresenter) {
            mPersonalDetailsPresenter= (PersonalDetailsPresenter) presenter;
        }
        else throw new InvalidParameterException();
    }

    @Override
    public void setCardApplicationFields() {
        String id = String.valueOf(mUserIdET.getText());
        String firstName = String.valueOf(mFirstNameET.getText());
        String lastName= String.valueOf(mLastNameET.getText());
        String authority=String.valueOf(mAuthorityIssuedET.getText());
        String countryIssued=String.valueOf(mCountryIssuedCardET.getText());
        String placeLost=String.valueOf(mPlaceLostET.getText());
        String EuCardNumber= String.valueOf(mEUcardNumberET.getText());
        String lostDate=String.valueOf(mLostDatePreview.getText());
        String expiryDate=String.valueOf(mExpiryDate_RenewalPreview.getText());
        String birthDate=String.valueOf(mBirthDatePreview.getText());
        String exchangeCardExpiry=String.valueOf(mDateOfExpiryEUcardPreview.getText());

        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date mLostDate = df.parse(lostDate);
            Date mExpiryDate = df.parse(expiryDate);
            Date mBirthDate = df.parse(birthDate);
            Date mExpiryEUcardDate = df.parse(exchangeCardExpiry);

            mCardApplication.getDetails().setDriverID(id);
            mCardApplication.getDetails().setFirstNameLatin(firstName);
            mCardApplication.getDetails().setSurNameLatin(lastName);
            mCardApplication.getDetails().setDriverBirthDate(mBirthDate);
            mCardApplication.getDetails().setAuthorityIssuedCard(authority);
            mCardApplication.getDetails().setCountryIssuedCard(countryIssued);
            mCardApplication.getDetails().setPlaceOfLoss(placeLost);
            mCardApplication.getDetails().setCardNumber(EuCardNumber);
            mCardApplication.getDetails().setDateOfLoss(mLostDate);

            if(exchangeCardExpiry!=null){mCardApplication.getDetails().setDateOfExpiry(mExpiryDate);}
            else if(expiryDate!=null){ mCardApplication.getDetails().setDateOfExpiry(mExpiryEUcardDate);}

        } catch (ParseException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void showLostOrStolenFields() {
        mLostElements.setVisibility(View.VISIBLE);
    }

    @Override
    public void showExchangeFields() {
        mExchangeElements.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRenewalFields() {
        mRenewalElements.setVisibility(View.VISIBLE);
    }

    @Override
    @SuppressLint({"SimpleDateFormat","DefaultLocale"})
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            String dateString = String.format("%d/%d/%d", dayOfMonth, ++month, year);
            mBirthDatePreview.setText(dateString);
            mBirthDatePreview.setTextSize(25);
            mBirthDatePreview.setTypeface(Typeface.DEFAULT);

    }

    @Override
    public DatePickerDialog initializeDatePickerDialog(int year, int month, int day) {
        return new DatePickerDialog(
                Objects.requireNonNull(getContext()),
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                this,
                year, month, day);
    }

    @Override
    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = initializeDatePickerDialog(year, month, day);

        Objects.requireNonNull(getActivity())
                .runOnUiThread(datePickerDialog::show);
    }

    @Override
    public void setCurrentUser(User user) {
        this.mUser = user;
    }

    @Override
    public void setCurrentCardApplication(CardApplication cardApplication) {
        this.mCardApplication = cardApplication;
    }

    @Override
    public void navigate() {
        Intent intent = prepareIntent();
        mNavigator.navigateWith(intent);
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }


    @Override
    public Intent prepareIntent() {
        Intent intent = new Intent();
        intent.putExtra(IntentKeys.USER_KEY, mUser);
        intent.putExtra(IntentKeys.CARD_APPLICATION_KEY, mCardApplication);
        return intent;
    }
}
