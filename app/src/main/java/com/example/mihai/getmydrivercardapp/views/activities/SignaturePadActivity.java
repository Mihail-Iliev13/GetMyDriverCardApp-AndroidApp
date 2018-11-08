package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.SignaturePadFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SignaturePadPresenter;
import com.mobsandgeeks.saripaar.Validator;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignaturePadActivity extends DaggerAppCompatActivity implements Navigator{

    @Inject
    SignaturePadFragment mSignaturePadFragment;

    @Inject
    SignaturePadPresenter mSignaturePadPresenter;

    private Validator mValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        Intent intent = getIntent();

        User user = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        CardApplication cardApplication = (CardApplication)
                intent.getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);

        mSignaturePadFragment.setPresenter(mSignaturePadPresenter);
        mSignaturePadFragment.setCurrentUser(user);
        mSignaturePadFragment.setCurrentCardApplication(cardApplication);
        mSignaturePadFragment.setNavigator(this);

        mValidator = new Validator(mSignaturePadFragment);
        mValidator.setValidationListener(mSignaturePadFragment);
        mValidator.setValidationMode(Validator.Mode.BURST);

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
        mSignaturePadPresenter
                .setValidator(mValidator);
    }

    @Override
    public void navigateWith(Intent intent) {
        intent.setClass(this, ApplicationStatusActivity.class);
        startActivity(intent);
    }
}
