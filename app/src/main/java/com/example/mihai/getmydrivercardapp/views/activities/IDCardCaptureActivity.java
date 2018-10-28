package com.example.mihai.getmydrivercardapp.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mihai.getmydrivercardapp.ImageAttribute;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.PersonalDetails;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ImageCapturePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class IDCardCaptureActivity extends AppCompatActivity {

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

        User user = new User();
        CardApplication cardApplication = new CardApplication();
        user.setCardApplications(new ArrayList<>());
        user.addCardApplication(cardApplication);
        cardApplication.setDetails(new PersonalDetails());

        mImageCaptureFragment.setCurrentUser(user);
        mImageCaptureFragment.setCurrentCardApplication(cardApplication);
        mImageCaptureFragment.setImageAttribute(ImageAttribute.ID_CARD_IMAGE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, mImageCaptureFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mImageCapturePresenter.subscribe(mImageCaptureFragment);
    }
}

