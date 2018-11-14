package com.example.mihai.getmydrivercardapp.views.activities.adminactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.views.activities.LoginActivity;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.CardApplicationListFragment;
import com.example.mihai.getmydrivercardapp.views.fragments.SearchToolBarFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.CardApplicationListPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SearchToolBarPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class CardApplicationListActivity extends DaggerAppCompatActivity implements Navigator {

    @Inject
    CardApplicationListPresenter mCardApplicationListPresenter;
    @Inject
    CardApplicationListFragment mCardApplicationListFragment;
    @Inject
    SearchToolBarPresenter mSearchToolBarPresenter;
    @Inject
    SearchToolBarFragment mSearchToolbarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);
        ButterKnife.bind(this);

        mCardApplicationListFragment
                .setPresenter(mCardApplicationListPresenter);

        mSearchToolbarFragment
                .setPresenter(mSearchToolBarPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.toolbar_fragment, mSearchToolbarFragment)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.list_fragment, mCardApplicationListFragment)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setSupportActionBar(mSearchToolbarFragment.getToolbar());
    }

    @Override
    public void onResume() {
        super.onResume();
        mCardApplicationListPresenter
                .subscribe(mCardApplicationListFragment);
        mSearchToolBarPresenter
                .subscribe(mSearchToolbarFragment);
        mSearchToolBarPresenter
                .setCardApplicationListView(mCardApplicationListFragment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //defines the outlook of the toolbar search view
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchToolbarFragment
                .getSearchView()
                .setMenuItem(searchItem);

        MenuItem logOutButton = menu.findItem(R.id.log_out);
        mSearchToolbarFragment
                .getSearchView()
                .setMenuItem(logOutButton);

        logOutButton.setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(this, LoginActivity.class);
            navigateWith(intent);
           return true;
        });

        return true;
    }

    @Override
    public void navigateWith(Intent intent) {
        startActivity(intent);
        ActivityCompat.finishAffinity(this);
    }
}
