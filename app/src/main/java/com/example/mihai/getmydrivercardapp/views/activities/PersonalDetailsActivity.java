package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.Constants;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.PersonalDetailsFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.PersonalDetailsPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class PersonalDetailsActivity extends DaggerAppCompatActivity {

    @Inject
    PersonalDetailsFragment mPersonalDetailsFragment;
    @Inject
    PersonalDetailsPresenter mPersonalDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(Constants.USER_KEY);
        CardApplication cardApplication = (CardApplication) intent
                .getSerializableExtra(Constants.CARD_APPLICATION_KEY);


        mPersonalDetailsFragment.setPresenter(mPersonalDetailsPresenter);
        mPersonalDetailsFragment.setCurrentUser(user);
        mPersonalDetailsFragment.setCurrentCardApplication(cardApplication);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment1, mPersonalDetailsFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPersonalDetailsPresenter.subscribe(mPersonalDetailsFragment);
    }
}