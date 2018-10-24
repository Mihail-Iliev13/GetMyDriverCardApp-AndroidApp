package com.example.mihai.getmydrivercardapp.views.activities;

import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.views.fragments.LogInFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.LogInPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity{

    @Inject
    LogInFragment mLoginFragment;

    @Inject
    LogInPresenter mLogInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginFragment.setPresenter(mLogInPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_fragment, mLoginFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            mLogInPresenter.subscribe(mLoginFragment);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}

