package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.StringConstants;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.SignaturePadFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SignaturePadPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignaturePadActivity extends DaggerAppCompatActivity {

    @Inject
    SignaturePadFragment mSignaturePadFragment;

    @Inject
    SignaturePadPresenter mSignaturePadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        Intent intent = getIntent();
        ArrayList<ImageModel> images = (ArrayList<ImageModel>) intent
                .getSerializableExtra(StringConstants.IMAGE_LIST_KEY);
        User user = (User) intent.getSerializableExtra(StringConstants.USER_KEY);
        CardApplication cardApplication = (CardApplication)
                intent.getSerializableExtra(StringConstants.CARD_APPLICATION_KEY);

        mSignaturePadFragment.setImages(images);
        mSignaturePadFragment.setPresenter(mSignaturePadPresenter);
        mSignaturePadFragment.setCurrentUser(user);
        mSignaturePadFragment.setCurrentCardApplication(cardApplication);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mSignaturePadFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSignaturePadPresenter
                .subscribe(mSignaturePadFragment);
    }
}
