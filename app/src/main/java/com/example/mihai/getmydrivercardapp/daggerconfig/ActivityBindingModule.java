package com.example.mihai.getmydrivercardapp.daggerconfig;


import com.example.mihai.getmydrivercardapp.views.activities.ApplicationReasonActivity;
import com.example.mihai.getmydrivercardapp.views.activities.ApplicationStatusActivity;
import com.example.mihai.getmydrivercardapp.views.activities.CardApplicationDetailsActivity;
import com.example.mihai.getmydrivercardapp.views.activities.CardApplicationListActivity;
import com.example.mihai.getmydrivercardapp.views.activities.ContactDetailsActivity;
import com.example.mihai.getmydrivercardapp.views.activities.DrivingLicenseCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.activities.IDCardCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.activities.LoginActivity;
import com.example.mihai.getmydrivercardapp.views.activities.OldCardCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.activities.PersonalDetailsActivity;
import com.example.mihai.getmydrivercardapp.views.activities.SelfieCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.activities.SignaturePadActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped()
    @ContributesAndroidInjector(modules = ServiceModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScoped()
    @ContributesAndroidInjector()
    abstract SelfieCaptureActivity selfieCaptureActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract IDCardCaptureActivity idCardCaptureActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract DrivingLicenseCaptureActivity drivingLicenseCaptureActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract OldCardCaptureActivity oldCardCaptureActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract SignaturePadActivity signaturePadActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract CardApplicationListActivity cardApplicationListActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract ApplicationStatusActivity applicationStatusActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract CardApplicationDetailsActivity cardApplicationDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract PersonalDetailsActivity personalDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract ApplicationReasonActivity applicationReasonActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract ContactDetailsActivity contactDetailsActivity();

}