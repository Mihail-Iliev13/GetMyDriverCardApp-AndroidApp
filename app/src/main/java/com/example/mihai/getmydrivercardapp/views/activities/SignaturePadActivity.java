package com.example.mihai.getmydrivercardapp.views.activities;

import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.PersonalDetails;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.SignaturePadFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SignaturePadPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignaturePadActivity extends DaggerAppCompatActivity {

    @Inject
    SignaturePadFragment mSignaturePadFragment;

    @Inject
    SignaturePadPresenter mSignaturePadPresenter;

    private User mUser;
    private CardApplication mCardApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        mSignaturePadFragment.setPresenter(mSignaturePadPresenter);
        mUser = new User();
        mCardApplication = new CardApplication();
        mCardApplication.setDetails(new PersonalDetails());
        mUser.setCardApplications(new ArrayList<>());
        mUser.addCardApplication(mCardApplication);

        mSignaturePadFragment.setCurrentUser(mUser);
        mSignaturePadFragment.setCurrentCardApplication(mCardApplication);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mSignaturePadFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSignaturePadPresenter.subscribe(mSignaturePadFragment);
    }
}
