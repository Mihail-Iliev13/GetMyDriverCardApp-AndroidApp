package com.example.mihai.getmydrivercardapp.views.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.views.fragments.CardApplicationListFragment;
import com.example.mihai.getmydrivercardapp.views.fragments.SearchToolBarFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SearchToolBarPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class CardApplicationListActivity extends DaggerAppCompatActivity {

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
    protected void onResume() {
        super.onResume();
        mCardApplicationListPresenter
                .subscribe(mCardApplicationListFragment);
        mSearchToolBarPresenter
                .subscribe(mSearchToolbarFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        mSearchToolbarFragment
                .getSearchView()
                .setMenuItem(menuItem);

        return true;
    }
}
