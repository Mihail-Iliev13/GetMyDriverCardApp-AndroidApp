package com.example.mihai.getmydrivercardapp.views.activities.adminactivities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.CardApplicationDetailsFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.CardApplicationDetailsPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class CardApplicationDetailsActivity extends DaggerAppCompatActivity {

    @Inject
    CardApplicationDetailsFragment mCardApplicationDetailsFragment;

    @Inject
    CardApplicationDetailsPresenter mCardApplicationDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        mCardApplicationDetailsFragment.setPresenter(mCardApplicationDetailsPresenter);

        Intent intent = getIntent();
        CardApplication cardApplication =
                (CardApplication) intent.getSerializableExtra(IntentKeys.CARD_APPLICATION_KEY);
        mCardApplicationDetailsFragment.setCardApplication(cardApplication);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mCardApplicationDetailsFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardApplicationDetailsPresenter
                .subscribe(mCardApplicationDetailsFragment);
    }
}
