package com.example.mihai.getmydrivercardapp.views.activities;

import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.ImageAttribute;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ImageCapturePresenter;

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

        mImageCaptureFragment.setPresenter(mImageCapturePresenter);
        mImageCaptureFragment.setCurrentUser(mUser);
        mImageCaptureFragment.setCurrentCardApplication(mCardApplication);
        mImageCaptureFragment.setImageAttribute(ImageAttribute.OLD_CARD_IMAGE);

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
    }
}
