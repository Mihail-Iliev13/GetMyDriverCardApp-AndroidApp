package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.enums.ImageAttribute;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ImageCapturePresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class OldCardCaptureActivity extends DaggerAppCompatActivity {

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
        mImageCaptureFragment.setPresenter(mImageCapturePresenter);
        mImageCaptureFragment.setCurrentUser(mUser);
        mImageCaptureFragment.setCurrentCardApplication(mCardApplication);

        mImageCaptureFragment.setImageModel(imageModel);

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

        mImageCaptureFragment.setInstructionMessage("Take a horizontal picture of your existing tachograph card!");
    }
}
