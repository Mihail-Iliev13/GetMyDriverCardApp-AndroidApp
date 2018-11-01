package com.example.mihai.getmydrivercardapp.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ContactDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ContactDetailsPresenter;

import java.security.InvalidParameterException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailsFragment extends Fragment implements ContactDetailsView {

    @BindView(R.id.et_address)
    EditText mAddressET;

    @BindView(R.id.et_email)
    EditText mEmailET;

    @BindView(R.id.et_phone_number)
    EditText mPhoneNumberET;

    @BindView(R.id.button_next)
    Button mButtonNext;

    private ContactDetailsPresenter mContactDetailsPresenter;
    private CardApplication mCardApplication;
    private User mUser;

    public ContactDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);
        ButterKnife.bind(this, view);

        mButtonNext.setOnClickListener(v -> mContactDetailsPresenter.handleOnClickNext());


        return view;

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
String address = String.valueOf(mAddressET.getText());
String email = String.valueOf(mEmailET.getText());
String phoneNumber = String.valueOf(mPhoneNumberET.getText());

mCardApplication.getDetails().setAddress(address);
mCardApplication.getDetails().setAddress(address);
mCardApplication.getDetails().setAddress(phoneNumber);
    }

    @Override
    public void navigateToNextView() {
// Intent newIntent
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof  ContactDetailsPresenter) {
            mContactDetailsPresenter= (ContactDetailsPresenter) presenter;
        }
        else throw new InvalidParameterException();
    }
}
