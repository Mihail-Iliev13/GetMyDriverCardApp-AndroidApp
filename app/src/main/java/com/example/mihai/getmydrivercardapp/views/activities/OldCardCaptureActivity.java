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

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class OldCardCaptureActivity extends DaggerAppCompatActivity implements Navigator {

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

        ImageModel imageModel = new ImageModel();
        imageModel.setImageAttribute(ImageAttribute.OLD_CARD_IMAGE);

        Intent intent = getIntent();
        mUser = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        mCardApplication = (CardApplication) intent.getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);
        mCardApplication.getDetails().getImages().add(imageModel);

        mImageCaptureFragment.setPresenter(mImageCapturePresenter);
        mImageCaptureFragment.setCurrentUser(mUser);
        mImageCaptureFragment.setCurrentCardApplication(mCardApplication);

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

        mImageCaptureFragment
                .setInstructionMessage("Take a horizontal picture of your existing tachograph card!");
    }

    @Override
    public void navigateWith(Intent intent) {

        List<ImageModel> images = ((CardApplication)intent.getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY))
                .getDetails().getImages();
        images.set(3, images.get(0));

        intent.setClass(this, SignaturePadActivity.class);
        startActivity(intent);
    }
}
