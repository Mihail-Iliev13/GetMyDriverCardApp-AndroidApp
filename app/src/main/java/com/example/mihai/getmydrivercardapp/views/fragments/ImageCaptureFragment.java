package com.example.mihai.getmydrivercardapp.views.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.enums.ImageAttribute;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ImageCaptureView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ImageCapturePresenter;

import java.security.InvalidParameterException;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageCaptureFragment extends Fragment implements ImageCaptureView {

    @BindView(R.id.tv_take_picture) TextView mTextMessage;
    @BindView(R.id.btn_capture_image) Button mCaptureImageButton;
    @BindView(R.id.btn_proceed) Button mProceedButton;
    @BindView(R.id.img_picture) ImageView mImageView;
    @BindView(R.id.txt_desc) TextView mTxtPreview;
    @BindView(R.id.rl_buttons) RelativeLayout mButtons;

    private ImageCapturePresenter mPresenter;
    private User mUser;
    private CardApplication mCardApplication;
    private ImageModel mImageModel;
    private Navigator mNavigator;

    @Inject
    public ImageCaptureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_image_capture, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.handleActivityResult(requestCode, resultCode, data, getActivity());

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if (mImageView.getDrawable() != null) {
                RelativeLayout.LayoutParams layoutParams =
                        (RelativeLayout.LayoutParams)mCaptureImageButton.getLayoutParams();
                layoutParams.removeRule(RelativeLayout.CENTER_IN_PARENT);
                mCaptureImageButton.setLayoutParams(layoutParams);
                mProceedButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick(R.id.btn_capture_image)
    public void captureImage () {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mPresenter.openCamera(this);
        });
    }

    @OnClick(R.id.btn_proceed)
    public void proceed () {
        Bitmap bitmap = ((BitmapDrawable)mImageView
                .getDrawable())
                .getBitmap();

        mPresenter.handleOnProceedClick(bitmap, mImageModel);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {

        if (mImageModel.getImageAttribute() == ImageAttribute.SELFIE_IMAGE) {
            bitmap = flipImage(bitmap, -1.0f, 1.0f);
        } else {
//            Matrix matrix = new Matrix();
//            matrix.postRotate(90);
//            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
//            bitmap = Bitmap.createBitmap(scaledBitmap, 0,0,scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        }

            mImageView.setImageBitmap(bitmap);
            mTxtPreview.setVisibility(View.GONE);
            mImageView.setVisibility(View.VISIBLE);
    }


    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof ImageCapturePresenter) {
            this.mPresenter = (ImageCapturePresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
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
    public void setImageModel(ImageModel image) {
        this.mImageModel = image;
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

    @Override
    public void setInstructionMessage(String message) {
        mTextMessage.setText(message);
    }

    private Bitmap flipImage(Bitmap bitmap, float sx, float sy) {
        Matrix matrix = new Matrix();
        matrix.preScale(sx, sy);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
    }

}
