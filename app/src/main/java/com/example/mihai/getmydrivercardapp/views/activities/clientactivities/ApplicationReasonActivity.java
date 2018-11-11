package com.example.mihai.getmydrivercardapp.views.activities.clientactivities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.PersonalDetails;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.ApplicationReasonFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationReasonPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ApplicationReasonActivity extends DaggerAppCompatActivity implements Navigator {

    @Inject
    ApplicationReasonFragment mApplicationReasonFragment;
    @Inject
    ApplicationReasonPresenter mApplicationReasonPresenter;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        Intent intent = getIntent();
        mUser = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);

        CardApplication cardApplication = initializeNewCardApplication();
        mApplicationReasonFragment.setPresenter(mApplicationReasonPresenter);
        mApplicationReasonFragment.setLoggedUser(mUser);
        mApplicationReasonFragment.setCurrentCardApplication(cardApplication);
        mApplicationReasonFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mApplicationReasonFragment)
                .commit();
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

    private CardApplication initializeNewCardApplication() {
        CardApplication cardApplication = new CardApplication();
        cardApplication.setStatus(CardApplicationStatus.NEW);
        PersonalDetails personalDetails = new PersonalDetails();
        cardApplication.setDetails(personalDetails);
        return cardApplication;
    }
}
