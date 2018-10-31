package com.example.mihai.getmydrivercardapp.views.activities;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.views.adapters.ViewPagerAdapter;
import com.example.mihai.getmydrivercardapp.views.fragments.ApplicationFillingFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.ApplicationFillingPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static android.databinding.DataBindingUtil.setContentView;

public class ApplicationFilling extends DaggerAppCompatActivity {

    @BindView(R.id.lv_applications)
    private Toolbar mToolbar;

    @BindView(R.id.viewpager)
    private ViewPager mViewPager;

    private ViewPagerAdapter mViewPagerAdapter;


    @Inject
    ApplicationFillingPresenter mAppFillingPresenter;
    @Inject
    ApplicationFillingFragment mAppFillingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_filling);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mViewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());

    }
}
