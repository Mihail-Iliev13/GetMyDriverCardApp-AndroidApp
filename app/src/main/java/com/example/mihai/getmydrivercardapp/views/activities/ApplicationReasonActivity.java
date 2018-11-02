package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.Constants;
import com.example.mihai.getmydrivercardapp.Navigator;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.PersonalDetails;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.views.fragments.ApplicationReasonFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ApplicationReasonPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ApplicationReasonActivity extends DaggerAppCompatActivity implements Navigator {

    @Inject
    ApplicationReasonFragment mApplicationReasonFragment;
    @Inject
    ApplicationReasonPresenter mApplicationReasonPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(Constants.USER_KEY);
        CardApplication cardApplication = initializeNewCardApplication();

        mApplicationReasonFragment.setPresenter(mApplicationReasonPresenter);
        mApplicationReasonFragment.setCurrentUser(user);
        mApplicationReasonFragment.setCurrentCardApplication(cardApplication);
        mApplicationReasonFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mApplicationReasonFragment)
                .commit();
    }

    private CardApplication initializeNewCardApplication() {
        CardApplication cardApplication = new CardApplication();
        cardApplication.setStatus(CardApplicationStatus.NEW);
        PersonalDetails personalDetails = new PersonalDetails();
        cardApplication.setDetails(personalDetails);
        return cardApplication;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApplicationReasonPresenter
                .subscribe(mApplicationReasonFragment);
    }

    @Override
    public void navigateWith(Intent intent) {
        intent.setClass(this, PersonalDetailsActivity.class);
        startActivity(intent);
    }
}
