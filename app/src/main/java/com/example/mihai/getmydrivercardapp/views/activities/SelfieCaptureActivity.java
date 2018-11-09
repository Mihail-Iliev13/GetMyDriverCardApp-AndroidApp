package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.enums.ImageAttribute;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ImageCapturePresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SelfieCaptureActivity extends DaggerAppCompatActivity implements Navigator {

    @Inject
    ImageCaptureFragment mImageCaptureFragment;
    @Inject
    ImageCapturePresenter mImageCapturePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        ImageModel imageModel = new ImageModel();
        imageModel.setImageAttribute(ImageAttribute.SELFIE_IMAGE);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        CardApplication cardApplication = (CardApplication) intent
                .getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);
        cardApplication.getDetails().getImages().add(imageModel);

        mImageCaptureFragment.setPresenter(mImageCapturePresenter);
        mImageCaptureFragment.setCurrentUser(user);
        mImageCaptureFragment.setCurrentCardApplication(cardApplication);

        mImageCaptureFragment.setImageModel(imageModel);
        mImageCaptureFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mImageCaptureFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mImageCapturePresenter
                .subscribe(mImageCaptureFragment);

        mImageCaptureFragment.setInstructionMessage("Take a vertical portrait picture of yourself");
    }

    @Override
    public void navigateWith(Intent intent) {
        intent.setClass(this, IDCardCaptureActivity.class);
        startActivity(intent);
    }
}
