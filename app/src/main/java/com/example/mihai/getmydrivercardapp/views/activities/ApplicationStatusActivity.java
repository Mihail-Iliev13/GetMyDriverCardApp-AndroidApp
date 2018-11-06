package com.example.mihai.getmydrivercardapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.ApplicationStatusFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationStatusPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ApplicationStatusActivity extends DaggerAppCompatActivity {

    @Inject
    ApplicationStatusPresenter mApplicationStatusPresenter;

    @Inject
    ApplicationStatusFragment mApplicationStatusFragment;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        Intent intent = getIntent();

        mUser = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        mApplicationStatusFragment.setUser(mUser);
        mApplicationStatusFragment.setPresenter(mApplicationStatusPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mApplicationStatusFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApplicationStatusPresenter
                .subscribe(mApplicationStatusFragment);
    }
}
