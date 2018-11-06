package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.ContactDetailsFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ContactDetailsPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ContactDetailsActivity extends DaggerAppCompatActivity implements Navigator{

    @Inject
    ContactDetailsFragment mContactDetailsFragment;
    @Inject
    ContactDetailsPresenter mContactDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);
        Intent intent = getIntent();

        User user = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        CardApplication cardApplication = (CardApplication)intent
                .getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);


        mContactDetailsFragment.setPresenter(mContactDetailsPresenter);
        mContactDetailsFragment.setCurrentUser(user);
        mContactDetailsFragment.setCurrentCardApplication(cardApplication);
        mContactDetailsFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mContactDetailsFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContactDetailsPresenter.subscribe(mContactDetailsFragment);
    }

    @Override
    public void navigateWith(Intent intent) {
        intent.setClass(this, SelfieCaptureActivity.class);
        startActivity(intent);
    }
}
