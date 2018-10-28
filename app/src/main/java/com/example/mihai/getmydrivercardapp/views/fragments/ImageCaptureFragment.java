package com.example.mihai.getmydrivercardapp.views.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.ImageAttribute;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ImageCaptureView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ImageCapturePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageCaptureFragment extends Fragment implements ImageCaptureView {

    @BindView(R.id.btn_capture_image)
    Button mCaptureImageButton;

    @BindView(R.id.btn_proceed)
    Button mProceedButton;

    @BindView(R.id.img_picture)
    ImageView mImageView;

    @BindView(R.id.txt_desc)
    TextView mTxtPreview;

    private ImageCapturePresenter mPresenter;
    private User mUser;
    private CardApplication mCardApplication;
    private ImageAttribute mImageAttribute;


    @Inject
    public ImageCaptureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_image_capture, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.handleActivityResult(requestCode, resultCode, data, getActivity());

        if (mImageView.getDrawable() != null) {
            mCaptureImageButton.setGravity(Gravity.LEFT);
            mProceedButton.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_capture_image)
    public void captureImage () {
        mPresenter.openCamera(this);
    }

    @OnClick(R.id.btn_proceed)
    public void proceed () {
        Bitmap bitmap = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
        byte[] byteImage = mPresenter.convertBitmapToByteArray(bitmap);
        mPresenter.setValueToImageAttribute(mCardApplication, mImageAttribute, byteImage);

        Toast.makeText(getContext(),
                "To next activity!",
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof ImageCapturePresenter) {
            this.mPresenter = (ImageCapturePresenter) presenter;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
        mTxtPreview.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);
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
    public void setImageAttribute(ImageAttribute imageAttribute) {
        this.mImageAttribute = imageAttribute;
    }
}
