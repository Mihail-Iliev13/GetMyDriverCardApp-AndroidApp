package com.example.mihai.getmydrivercardapp.views.activities;

import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.views.fragments.CardApplicationListFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class CardApplicationListActivity extends DaggerAppCompatActivity {

    @Inject
    CardApplicationListPresenter mCardApplicationListPresenter;

    @Inject
    CardApplicationListFragment mCardApplicationListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_one_fragment);

        mCardApplicationListFragment.setPresenter(mCardApplicationListPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, mCardApplicationListFragment)
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardApplicationListPresenter.subscribe(mCardApplicationListFragment);
    }
}
