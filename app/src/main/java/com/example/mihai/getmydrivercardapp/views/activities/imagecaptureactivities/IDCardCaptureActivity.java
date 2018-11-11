package com.example.mihai.getmydrivercardapp.views.activities.imagecaptureactivities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.utils.ImageHolder;
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

public class IDCardCaptureActivity extends DaggerAppCompatActivity implements Navigator {

    public static final String MESSAGE = "Take a horizontal picture of your ID card";

    @Inject
    ImageCaptureFragment mImageCaptureFragment;
    @Inject
    ImageCapturePresenter mImageCapturePresenter;
    private ImageModel mImageModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        mImageModel = new ImageModel();
        mImageModel.setImageAttribute(ImageAttribute.ID_CARD_IMAGE);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        CardApplication cardApplication = (CardApplication) intent
                .getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);

        mImageCaptureFragment.setPresenter(mImageCapturePresenter);
        mImageCaptureFragment.setLoggedUser(user);
        mImageCaptureFragment.setCurrentCardApplication(cardApplication);
        mImageCaptureFragment.setImageModel(mImageModel);
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

        mImageCaptureFragment
                .setInstructionMessage(MESSAGE);
    }

    @Override
    public void navigateWith(Intent intent) {
        ImageHolder.setIDCard(mImageModel);
        intent.setClass(this, DrivingLicenseCaptureActivity.class);
        startActivity(intent);
    }
}

