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
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.CardApplicationListActivity;
import com.example.mihai.getmydrivercardapp.views.activities.SignaturePadActivity;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.LogInView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.LogInPresenter;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.EasyImageConfig;

public class LogInFragment extends Fragment implements LogInView {

    @BindView(R.id.et_email)
    EditText mEmail;

    @BindView(R.id.et_password)
    EditText mPassword;

    @BindView(R.id.btn_log_in)
    Button mLoginButton;

    @BindView(R.id.btn_sign_up)
    Button mSignupButton;

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
       }
    }

    @OnClick(R.id.btn_log_in)
    public void logInOnClick(){
        String email = String.valueOf(mEmail.getText());
        String password = String.valueOf(mPassword.getText());
        try {
            mLogInPresenter.logIn(email, password);
        } catch (IOException e) {
            e.printStackTrace();
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

        Intent intent = new Intent(getContext(), CardApplicationListActivity.class);
        startActivity(intent);
//        getActivity().runOnUiThread( () -> {
//            Toast.makeText(getContext(),
//                    "There is no existing user with email: " + email + "Try to Sign Up first",
//                    Toast.LENGTH_LONG)
//                    .show();
//        });
    }

    @Override
    public void showNoMatchingPasswordToast() {
        getActivity().runOnUiThread( () -> {
            Toast.makeText(getContext(), "Password is invalid!",
                    Toast.LENGTH_LONG)
                    .show();
        });

       EasyImage.openCamera(this, EasyImageConfig.REQ_TAKE_PICTURE);
    }

    @Override
    public void showCardApplicationStatus(CardApplication cardApplication) {
        getActivity().runOnUiThread( () -> {
            Toast.makeText(getContext(), "To card Application Status",
                    Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showFillCardApplicationForm(User user) {
        Intent intent = new Intent(getContext(), SignaturePadActivity.class);
        startActivity(intent);
//        getActivity().runOnUiThread( () -> {
//            Toast.makeText(getContext(), "Fill the form",
//                    Toast.LENGTH_LONG)
//                    .show();
//        });
    }

    @Override
    public void showUserAlreadyExists() {
        getActivity().runOnUiThread( () -> {
            Toast.makeText(getContext(), "User with this email already exists",
                    Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showAllPendingApplications() {

    }

    @Override
    public void showError(Exception e) {
        getActivity().runOnUiThread( () -> {
            Toast.makeText(getContext(), "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        });
    }
}
