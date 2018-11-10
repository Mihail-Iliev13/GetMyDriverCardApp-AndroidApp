package com.example.mihai.getmydrivercardapp.views.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.SignaturePadView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SignaturePadPresenter;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Order;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignaturePadFragment extends Fragment implements SignaturePadView, Validator.ValidationListener {

    @Order(1)
    @BindView(R.id.sp_signature_pad)
    SignaturePad mSignaturePad;

    @BindView(R.id.btn_clear_signature)
    Button mClearButton;

    @BindView(R.id.btn_submit)
    Button mSubmitButton;

    @Order(2)
    @BindView(R.id.cb_checkbox)
    @Checked
    CheckBox mCheckBox;

    @BindView(R.id.loading)
    ProgressBar mLoadingView;

    @BindView(R.id.rl_content)
    RelativeLayout mLayout;

    private User mUser;
    private CardApplication mCardApplication;
    private SignaturePadPresenter mSignaturePadPresenter;
    private Navigator mNavigator;


    @Inject
    public SignaturePadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signature_pad, container, false);
        ButterKnife.bind(this, view);

        mClearButton.setEnabled(false);
        mSubmitButton.setEnabled(false);

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {

            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                  mClearButton.setEnabled(true);
                  mSubmitButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSubmitButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mSubmitButton.setOnClickListener(v -> {
            mSignaturePadPresenter.validate();
        });

        return view;
    }


    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof SignaturePadPresenter) {
            this.mSignaturePadPresenter = (SignaturePadPresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
    }

    @OnClick(R.id.btn_clear_signature)
    public void clearOnClick(){
        mSignaturePad.clear();
    }


    @Override
    public void showError(Exception e) {
        Toast.makeText(getContext(),
                "Error: " + e.getMessage(),
                Toast.LENGTH_SHORT)
                .show();
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
        return intent;
    }

    @Override
    public void onValidationSucceeded() {
        Bitmap bitmapImage = mSignaturePad.getSignatureBitmap();
        showLoading();
        try{
            mSignaturePadPresenter.assignSignature(bitmapImage, mCardApplication);
            mSignaturePadPresenter.assignDateOfSubmission(mCardApplication);
            mSignaturePadPresenter.saveUser(mUser, mCardApplication);
            mSignaturePadPresenter.saveImages(mUser, mCardApplication);
            hideLoading();
            navigate();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            Rule failedRule = error.getFailedRules().get(0);
            String message = failedRule.getMessage(getContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void showLoading() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mLayout.setVisibility(View.GONE);
            mLoadingView.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mLoadingView.setVisibility(View.GONE);
        });
    }
}
