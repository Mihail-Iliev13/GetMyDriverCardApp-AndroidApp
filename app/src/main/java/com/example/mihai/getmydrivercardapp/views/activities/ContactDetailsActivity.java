package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.Constants;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.ContactDetailsFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ContactDetailsPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ContactDetailsActivity extends DaggerAppCompatActivity {

    @Inject
    ContactDetailsFragment mContactDetailsFragment;
    @Inject
    ContactDetailsPresenter mContactDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);
        Intent intent = getIntent();

        User user = (User) intent.getSerializableExtra(Constants.USER_KEY);
        CardApplication cardApplication = (CardApplication)intent
                .getSerializableExtra(Constants.CARD_APPLICATION_KEY);


        mContactDetailsFragment.setPresenter(mContactDetailsPresenter);
        mContactDetailsFragment.setCurrentUser(user);
        mContactDetailsFragment.setCurrentCardApplication(cardApplication);

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
}
