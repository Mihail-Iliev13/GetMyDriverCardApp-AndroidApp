package com.example.mihai.getmydrivercardapp.views.fragments;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.SignaturePadView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SignaturePadPresenter;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignaturePadFragment extends Fragment implements SignaturePadView {

    @BindView(R.id.sp_signature_pad) SignaturePad mSignaturePad;
    @BindView(R.id.btn_clear_signature) Button mClearButton;
    @BindView(R.id.btn_submit) Button mSubmitButton;

    private User mUser;
    private CardApplication mCardApplication;
    private SignaturePadPresenter mSignaturePadPresenter;
    private ArrayList<ImageModel> mImages;

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

    @OnClick(R.id.btn_submit)
    @SuppressLint("SimpleDateFormat")
    public void submitOnClick(){
        Bitmap bitmapImage = mSignaturePad.getSignatureBitmap();
        mSignaturePadPresenter.assignSignature(bitmapImage, mCardApplication);
        mSignaturePadPresenter.assignDateOfSubmission(mCardApplication);
        mSignaturePadPresenter.saveUser(mUser.getEmail(), mCardApplication);
        mSignaturePadPresenter.saveImages(mUser.getEmail(), mCardApplication.getDetails().getImages());
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
    public void setSignature(byte[] byteArrayImage) {
        mCardApplication.getDetails().setSignature(byteArrayImage);
    }

    @Override
    public void setImages(ArrayList<ImageModel> images) {
        this.mImages = images;
    }

}
