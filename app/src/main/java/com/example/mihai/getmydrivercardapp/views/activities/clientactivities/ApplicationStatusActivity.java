package com.example.mihai.getmydrivercardapp.views.activities.clientactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.LoginActivity;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.ApplicationStatusFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationStatusPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ApplicationStatusActivity extends DaggerAppCompatActivity implements Navigator{

    @Inject
    ApplicationStatusPresenter mApplicationStatusPresenter;

    @Inject
    ApplicationStatusFragment mApplicationStatusFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        Intent intent = getIntent();

        User user = (User) intent.getSerializableExtra(IntentKeys.USER_KEY);
        mApplicationStatusFragment.setUser(user);
        mApplicationStatusFragment.setPresenter(mApplicationStatusPresenter);
        mApplicationStatusFragment.setNavigator(this);

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

    @Override
    public void navigateWith(Intent intent) {
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        ActivityCompat.finishAffinity(this);
    }
}
