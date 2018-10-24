package com.example.mihai.getmydrivercardapp.daggerconfig.fragmentmodules;

import com.example.mihai.getmydrivercardapp.daggerconfig.FragmentScoped;
import com.example.mihai.getmydrivercardapp.views.fragments.LogInFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LogInFragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract LogInFragment logInFragment();
}
