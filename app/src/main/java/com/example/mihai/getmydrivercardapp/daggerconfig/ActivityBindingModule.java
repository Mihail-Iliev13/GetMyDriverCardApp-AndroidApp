package com.example.mihai.getmydrivercardapp.daggerconfig;


import com.example.mihai.getmydrivercardapp.views.activities.clientactivities.ApplicationReasonActivity;
import com.example.mihai.getmydrivercardapp.views.activities.clientactivities.ApplicationStatusActivity;
import com.example.mihai.getmydrivercardapp.views.activities.adminactivities.CardApplicationDetailsActivity;
import com.example.mihai.getmydrivercardapp.views.activities.adminactivities.CardApplicationListActivity;
import com.example.mihai.getmydrivercardapp.views.activities.clientactivities.ContactDetailsActivity;
import com.example.mihai.getmydrivercardapp.views.activities.imagecaptureactivities.DrivingLicenseCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.activities.imagecaptureactivities.IDCardCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.activities.LoginActivity;
import com.example.mihai.getmydrivercardapp.views.activities.imagecaptureactivities.OldCardCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.activities.clientactivities.PersonalDetailsActivity;
import com.example.mihai.getmydrivercardapp.views.activities.imagecaptureactivities.SelfieCaptureActivity;
import com.example.mihai.getmydrivercardapp.views.activities.clientactivities.SignaturePadActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped()
    @ContributesAndroidInjector(modules = ServiceBindingModule.class)
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