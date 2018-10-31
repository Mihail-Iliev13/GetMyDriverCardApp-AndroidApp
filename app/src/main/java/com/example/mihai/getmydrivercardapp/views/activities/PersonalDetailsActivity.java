package com.example.mihai.getmydrivercardapp.views.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.views.fragments.PersonalDetailsFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.PersonalDetailsPresenter;

import javax.inject.Inject;

public class PersonalDetailsActivity extends Activity {
    @Inject
    PersonalDetailsFragment personalDetailsFragment;
    @Inject
    PersonalDetailsPresenter personalDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
      /*
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment1,personalDetailsFragment)
                .commit();*/


    }
}
