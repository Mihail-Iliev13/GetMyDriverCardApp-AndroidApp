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

import com.example.mihai.getmydrivercardapp.Constants;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.ApplicationStatusActivity;
import com.example.mihai.getmydrivercardapp.views.activities.CardApplicationListActivity;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.LogInView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.LogInPresenter;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInFragment extends Fragment implements LogInView {

    @BindView(R.id.et_email)
    EditText mEmail;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.btn_log_in)
    Button mLoginButton;
    @BindView(R.id.btn_sign_up)
    Button mSignUpButton;

    private LogInPresenter mLogInPresenter;

    @Inject
    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

       if (presenter instanceof LogInPresenter) {
           this.mLogInPresenter = (LogInPresenter) presenter;
       } else {
           throw new InvalidParameterException();
       }
    }

    @OnClick(R.id.btn_log_in)
    public void logInOnClick() {
        String email = String.valueOf(mEmail.getText());
        String password = String.valueOf(mPassword.getText());

        try {
            mLogInPresenter.logIn(email, password);
        } catch (IOException e) {
            showError(e);
        }

    }

    @OnClick(R.id.btn_sign_up)
    public void signUpOnClick () {
        String email = String.valueOf(mEmail.getText());
        String password = String.valueOf(mPassword.getText());
        mLogInPresenter.signUp(email, password);
    }

    @Override
    public void showNoSuchUserToast(String email) {

        Objects.requireNonNull(getActivity()).runOnUiThread( () -> {
            Toast.makeText(getContext(),
                    "There is no existing user with email: " + email + "Try to Sign Up first",
                    Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showNoMatchingPasswordToast() {
        Objects.requireNonNull(getActivity()).runOnUiThread( () -> {
            Toast.makeText(getContext(), "Password is invalid!",
                    Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showCardApplicationStatus(CardApplication cardApplication) {
        Intent intent = new Intent(getContext(), ApplicationStatusActivity.class);
        intent.putExtra(Constants.CARD_APPLICATION_KEY, cardApplication);
        startActivity(intent);
    }

    @Override
    public void showFillCardApplicationForm(User user) {

        Intent intent = new Intent(getContext(), ApplicationStatusActivity.class);
        startActivity(intent);

//        getActivity().runOnUiThread( () -> {
//            Toast.makeText(getContext(), "Fill the form",
//                    Toast.LENGTH_LONG)
//                    .show();
//        });
    }

    @Override
    public void showUserAlreadyExists() {
        Objects.requireNonNull(getActivity()).runOnUiThread( () -> {
            Toast.makeText(getContext(), "User with this email already exists",
                    Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showAllCardApplications() {
        Intent intent = new Intent(getContext(), CardApplicationListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(Exception e) {
        Objects.requireNonNull(getActivity()).runOnUiThread( () -> {
            Toast.makeText(getContext(), "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        });
    }
}
