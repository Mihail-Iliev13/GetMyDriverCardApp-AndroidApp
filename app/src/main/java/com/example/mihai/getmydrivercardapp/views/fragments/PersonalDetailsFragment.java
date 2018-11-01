package com.example.mihai.getmydrivercardapp.views.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.PersonalDetailsPresenter;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalDetailsFragment extends Fragment implements PersonalDetailsView,DatePickerDialog.OnDateSetListener {

    @BindView(R.id.et_user_id)
    EditText mUserIdET;
    @BindView(R.id.et_first_name)
    EditText mFirstNameET;
    @BindView(R.id.et_last_name)
    EditText mLastNameET;
    @BindView(R.id.et_date_of_birth)
    DatePicker mDateOfBirthET;
    @BindView(R.id.button_next)
    Button mButtonNext;

    private PersonalDetailsPresenter mPersonalDetailsPresenter;
    private CardApplication mCardApplication;
    private User mUser;
    private Date mBirthDate;

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
    public void setUser(User user) {
        this.mUser=user;
    }

    @Override
    public void CardApplication(CardApplication cardApplication) {
    this.mCardApplication=cardApplication;
    }

    @Override
    public void assignValues() {
        String id= String.valueOf(mUserIdET.getText());
        String firstName = String.valueOf(mFirstNameET.getText());
        String lastName= String.valueOf(mLastNameET.getText());

        mCardApplication.getDetails().setDriverID(id);
        mCardApplication.getDetails().setFirstNameLatin(firstName);
        mCardApplication.getDetails().setSurNameLatin(lastName);
        mCardApplication.getDetails().setDriverBirthDate(mBirthDate);

    }

    @Override
    public void navigateToNextView() {
        //Intent newIntent=Intent(this,ContactDetailsActivity.class);
        //startActivity(newIntent);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            String dateString = dayOfMonth + "/" +  month + "/" + year;
        try {
            mBirthDate= new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }



}
