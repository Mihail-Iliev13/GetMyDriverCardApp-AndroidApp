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
    @BindView(R.id.tv_birth_date_preview) TextView mDatePreview;

    private PersonalDetailsPresenter mPersonalDetailsPresenter;
    private CardApplication mCardApplication;
    private User mUser;
    private Date mBirthDate;
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
        mButtonNext.setOnClickListener(v -> mPersonalDetailsPresenter.handleOnClickNext());
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

        mCardApplication.getDetails().setDriverID(id);
        mCardApplication.getDetails().setFirstNameLatin(firstName);
        mCardApplication.getDetails().setSurNameLatin(lastName);
        mCardApplication.getDetails().setDriverBirthDate(mBirthDate);
    }

    @Override
    @SuppressLint({"SimpleDateFormat","DefaultLocale"})
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        try {
            String dateString = String.format("%d/%d/%d", dayOfMonth, ++month, year);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mBirthDate = df.parse(dateString);
            mDatePreview.setText(dateString);
            mDatePreview.setTextSize(25);
            mDatePreview.setTypeface(Typeface.DEFAULT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
