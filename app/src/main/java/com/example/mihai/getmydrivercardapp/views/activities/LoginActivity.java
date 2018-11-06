package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.views.activities.interfaces.LogInNavigator;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.views.fragments.LogInFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.LogInPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity implements LogInNavigator{

    @Inject
    LogInFragment mLoginFragment;

    @Inject
    LogInPresenter mLogInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        mLoginFragment.setPresenter(mLogInPresenter);
        mLoginFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mLoginFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLogInPresenter.subscribe(mLoginFragment);
    }

    @Override
    public void navigateToApplicationStatus(Intent intent) {
        intent.setClass(this, ApplicationStatusActivity.class);
        navigateWith(intent);
    }

    @Override
    public void navigateToApplicationsList(Intent intent) {
        intent.setClass(this, CardApplicationListActivity.class);
        navigateWith(intent);
    }

    @Override
    public void navigateToApplicationReason(Intent intent) {
       intent.setClass(this, ApplicationReasonActivity.class);
       navigateWith(intent);
    }

    @Override
    public void navigateWith(Intent intent) {
        startActivity(intent);
    }
}

