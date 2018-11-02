package com.example.mihai.getmydrivercardapp.views.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mihai.getmydrivercardapp.Constants;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ContactDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ContactDetailsPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDetailsFragment extends Fragment implements ContactDetailsView {

    @BindView(R.id.et_email) EditText mEmail;
    @BindView(R.id.et_phone_number) EditText mPhoneNumber;
    @BindView(R.id.et_user_address) EditText mUserAddress;
    @BindView(R.id.btn_next) Button mButtonNext;

    private ContactDetailsPresenter mContactDetailsPresenter;
    private User mUser;
    private CardApplication mCardApplication;

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
        mButtonNext.setOnClickListener(v -> mContactDetailsPresenter.handleOnButtonNextClick());
        return view;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof ContactDetailsPresenter) {
            this.mContactDetailsPresenter = (ContactDetailsPresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void setCardApplicationFields() {
        String email = String.valueOf(mEmail.getText());
        String phoneNumber = String.valueOf(mPhoneNumber.getText());
        String address = String.valueOf(mUserAddress.getText());

        mCardApplication.getDetails().setAddress(address);
        mCardApplication.getDetails().setPhoneNumber(phoneNumber);
        mCardApplication.getDetails().setEmail(email);
    }

    @Override
    public void navigate(Class<? extends Activity> activity) {
        Intent intent = new Intent(getContext(), activity);
        intent.putExtra(Constants.USER_KEY, mUser);
        intent.putExtra(Constants.CARD_APPLICATION_KEY, mCardApplication);
        startActivity(intent);
    }

    @Override
    public void setCurrentUser(User user) {
        this.mUser = user;
    }

    @Override
    public void setCurrentCardApplication(CardApplication cardApplication) {
        this.mCardApplication = cardApplication;
    }
}
