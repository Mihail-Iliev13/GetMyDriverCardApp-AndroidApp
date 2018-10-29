package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.views.fragments.CardApplicationListFragment;
import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.fragments.LogInFragment;
import com.example.mihai.getmydrivercardapp.views.fragments.SearchToolBarFragment;
import com.example.mihai.getmydrivercardapp.views.fragments.SignaturePadFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ImageCaptureFragment imageCaptureFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract LogInFragment logInFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SignaturePadFragment signaturePadFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CardApplicationListFragment cardApplicationListFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchToolBarFragment searchToolBarFragment();
}
