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
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.Constants;
import com.example.mihai.getmydrivercardapp.Navigator;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.User;
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

    @BindView(R.id.et_email) EditText mEmail;
    @BindView(R.id.et_password) EditText mPassword;
    @BindView(R.id.btn_log_in) Button mLoginButton;
    @BindView(R.id.btn_sign_up) Button mSignUpButton;

    private LogInPresenter mLogInPresenter;
    private User mUser;
    private Navigator mNavigator;


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
    public void showNoExistingUserError(String email) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mEmail.setError("There is no existing user with email: " + email);
        });
    }

    @Override
    public void showNoMatchingPasswordError() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mPassword.setError("Invalid Password!");
        });
    }

    @Override
    public void showUserAlreadyExistsError(String email) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mEmail.setError("User with email " +  " already exists!");
        });
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }


    @Override
    public void showError(Exception e) {
        Objects.requireNonNull(getActivity()).runOnUiThread( () -> {
            Toast.makeText(getContext(), "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void navigate(Class<? extends Activity> activity) {
        Intent intent = new Intent(getContext(), activity);
        intent.putExtra(Constants.USER_KEY, mUser);
        startActivity(intent);
    }
}
