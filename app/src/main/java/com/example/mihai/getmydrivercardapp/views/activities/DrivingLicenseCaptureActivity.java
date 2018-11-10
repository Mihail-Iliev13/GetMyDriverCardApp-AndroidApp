package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.enums.ImageAttribute;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ImageCapturePresenter;

import java.util.EnumSet;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DrivingLicenseCaptureActivity extends DaggerAppCompatActivity implements Navigator {

    @Inject
    ImageCaptureFragment mImageCaptureFragment;

    @Inject
    ImageCapturePresenter mImageCapturePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);


        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        CardApplication cardApplication = (CardApplication) intent
                .getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);


        ImageModel imageModel = new ImageModel();
        imageModel.setImageAttribute(ImageAttribute.DRIVING_LICENSE_IMAGE);
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
        mImageCapturePresenter.subscribe(mImageCaptureFragment);
        mImageCaptureFragment.setInstructionMessage("Take a horizontal picture of your driving license");
    }

    @Override
    public void navigateWith(Intent intent) {
        CardApplication cardApplication = (CardApplication) intent.getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);
        CardApplicationReason reason = cardApplication.getCardApplicationReason();
        EnumSet<CardApplicationReason> set = EnumSet.of(CardApplicationReason.LOST, CardApplicationReason.NEW_CARD,
                CardApplicationReason.STOLEN, CardApplicationReason.DAMAGED, CardApplicationReason.WITHDRAWN);

        if (!set.contains(reason)) {
            intent.setClass(this, OldCardCaptureActivity.class);
        } else {
            intent.setClass(this, SignaturePadActivity.class);
        }

        List<ImageModel> images = ((CardApplication)intent.getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY))
                .getDetails().getImages();
        images.set(2, images.get(0));
        startActivity(intent);
    }
}
