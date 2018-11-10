package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.customannotations.dateformat.DateFormat;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.PersonalDetailsFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;
import com.mobsandgeeks.saripaar.Validator;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class PersonalDetailsActivity extends DaggerAppCompatActivity implements Navigator{

    @Inject
    PersonalDetailsFragment mPersonalDetailsFragment;
    @Inject
    PersonalDetailsPresenter mPersonalDetailsPresenter;

    private Validator mValidator;
    private User mUser;
    private CardApplication mCardApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            mUser = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
            mCardApplication = (CardApplication) intent
                    .getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);
        } else {
            mUser = (User) savedInstanceState.getSerializable(IntentKeys.USER_KEY);
            mCardApplication = (CardApplication) savedInstanceState
                    .getSerializable(IntentKeys.CARD_APPLICATION_KEY);
        }

        mPersonalDetailsFragment.setPresenter(mPersonalDetailsPresenter);
        mPersonalDetailsFragment.setCurrentUser(mUser);
        mPersonalDetailsFragment.setCurrentCardApplication(mCardApplication);
        mPersonalDetailsFragment.setNavigator(this);

        mValidator = new Validator(mPersonalDetailsFragment);
        mValidator.setValidationMode(Validator.Mode.BURST);
        mValidator.setValidationListener(mPersonalDetailsFragment);
        Validator.registerAnnotation(DateFormat.class);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment1, mPersonalDetailsFragment)
                .commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPersonalDetailsPresenter.subscribe(mPersonalDetailsFragment);
        mPersonalDetailsPresenter.setValidator(mValidator);
    }

    @Override
    public void navigateWith(Intent intent) {
        intent.setClass(this, ContactDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(IntentKeys.USER_KEY, mUser);
        outState.putSerializable(IntentKeys.CARD_APPLICATION_KEY, mCardApplication);
    }


}
