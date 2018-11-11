package com.example.mihai.getmydrivercardapp.views.activities.imagecaptureactivities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.utils.ImageHolder;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.enums.ImageAttribute;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.clientactivities.SignaturePadActivity;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ImageCapturePresenter;

import java.util.EnumSet;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DrivingLicenseCaptureActivity extends DaggerAppCompatActivity implements Navigator {

    public static final String MESSAGE = "Take a horizontal picture of your driving license";

    @Inject
    ImageCaptureFragment mImageCaptureFragment;
    @Inject
    ImageCapturePresenter mImageCapturePresenter;

    private ImageModel mImageModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        CardApplication cardApplication = (CardApplication) intent
                .getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);


        mImageModel = new ImageModel();
        mImageModel.setImageAttribute(ImageAttribute.DRIVING_LICENSE_IMAGE);

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
        mImageCapturePresenter.subscribe(mImageCaptureFragment);
        mImageCaptureFragment.setInstructionMessage(MESSAGE);
    }

    @Override
    public void navigateWith(Intent intent) {
        CardApplication cardApplication =
                (CardApplication) intent.getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);

        CardApplicationReason applicationReason = cardApplication.getCardApplicationReason();

        if (shouldGoToOldCardCaptureActivity(applicationReason)) {
            intent.setClass(this, OldCardCaptureActivity.class);
        } else {
            intent.setClass(this, SignaturePadActivity.class);
        }

        ImageHolder.setDrivingLicense(mImageModel);
        startActivity(intent);
    }

    private boolean shouldGoToOldCardCaptureActivity(CardApplicationReason applicationReason) {
        return !EnumSet.of(CardApplicationReason.LOST,
                CardApplicationReason.NEW_CARD,
                CardApplicationReason.STOLEN,
                CardApplicationReason.DAMAGED, CardApplicationReason.WITHDRAWN)
                .contains(applicationReason);
    }
}
