package com.example.mihai.getmydrivercardapp.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.views.fragments.ApplicationFillingFragment;
import com.example.mihai.getmydrivercardapp.views.presenters.ApplicationFillingPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static android.databinding.DataBindingUtil.setContentView;

public class ApplicationFilling extends DaggerAppCompatActivity {

    @Inject
    ApplicationFillingPresenter mAppFillingPresenter;
    @Inject
    ApplicationFillingFragment mAppFillingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_filling);
        ButterKnife.bind(this);

    }
}
