package com.example.mihai.getmydrivercardapp.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.customannotations.bgphoneprefix.BGPhoneNumber;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ContactDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ContactDetailsPresenter;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactDetailsFragment extends Fragment implements ContactDetailsView,
        Validator.ValidationListener {

    @BindView(R.id.et_email)
    @NotEmpty(sequence = 1)
    @Email(sequence = 3)
    @Order(3)
    EditText mEmail;

    @BindView(R.id.et_phone_number)
    @NotEmpty(sequence = 1)
    @Order(2)
    @Length( sequence = 2, max = 10, min = 10, message = "Invalid length! " +
            "The length should be exactly ten digits. " +
            "+359 prefix is unnecessary.")
    @BGPhoneNumber(sequence = 3)
    EditText mPhoneNumber;

    @BindView(R.id.et_user_address)
    @NotEmpty(sequence = 1)
    @Order(1)
    EditText mUserAddress;

    @BindView(R.id.btn_next)
    Button mButtonNext;

    private ContactDetailsPresenter mContactDetailsPresenter;
    private User mUser;
    private CardApplication mCardApplication;
    private Navigator mNavigator;

    @Inject
    public ContactDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_next)
    public void nextOnClick() {
        mContactDetailsPresenter.validate();
    }


    @Override
    public void setPresenter(ContactDetailsPresenter presenter) {
            this.mContactDetailsPresenter = presenter;
    }

    @Override
    public void showValidationError(View view, Rule failedRule) {
        String message = failedRule.getMessage(getContext());

        // Display error messages ;)
        if (view instanceof EditText) {
            ((EditText) view).setError(message);
        } else {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setCardApplicationFields() {

    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void navigate() {
        Intent intent = prepareIntent();
        mNavigator.navigateWith(intent);
    }

    @Override
    public Intent prepareIntent() {
        Intent intent = new Intent();
        intent.putExtra(IntentKeys.USER_KEY, mUser);
        intent.putExtra(IntentKeys.CARD_APPLICATION_KEY, mCardApplication);
        return intent;
    }

    @Override
    public void setLoggedUser(User user) {
        this.mUser = user;
    }

    @Override
    public void setCurrentCardApplication(CardApplication cardApplication) {
        this.mCardApplication = cardApplication;
    }

    @Override
    public void onValidationSucceeded() {
        String email = String.valueOf(mEmail.getText());
        String phoneNumber = String.valueOf(mPhoneNumber.getText());
        String address = String.valueOf(mUserAddress.getText());
        mContactDetailsPresenter.handleOnButtonNextClick(mCardApplication, address, phoneNumber, email);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        mContactDetailsPresenter.handleOnValidationFailed(errors);
    }
}
