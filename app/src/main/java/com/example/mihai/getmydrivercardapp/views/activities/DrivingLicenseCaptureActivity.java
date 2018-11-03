package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.Constants;
import com.example.mihai.getmydrivercardapp.ImageAttribute;
import com.example.mihai.getmydrivercardapp.Navigator;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ImageCapturePresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DrivingLicenseCaptureActivity extends DaggerAppCompatActivity implements Navigator {

    @Inject
    ImageCaptureFragment mImageCaptureFragment;

    @Inject
    ImageCapturePresenter mImageCapturePresenter;

    private User mUser;
    private CardApplication mCardApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(Constants.USER_KEY);
        CardApplication cardApplication = (CardApplication) intent
                .getSerializableExtra(Constants.CARD_APPLICATION_KEY);

        mImageCaptureFragment.setPresenter(mImageCapturePresenter);
        mImageCaptureFragment.setCurrentUser(user);
        mImageCaptureFragment.setCurrentCardApplication(cardApplication);
        mImageCaptureFragment.setImageAttribute(ImageAttribute.DRIVING_LICENSE_IMAGE);
        mImageCaptureFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mImageCaptureFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mImageCapturePresenter.subscribe(mImageCaptureFragment);
    }

    @Override
    public void navigateWith(Intent intent) {
        intent.setClass(this, SignaturePadActivity.class);
        startActivity(intent);
        }
}
