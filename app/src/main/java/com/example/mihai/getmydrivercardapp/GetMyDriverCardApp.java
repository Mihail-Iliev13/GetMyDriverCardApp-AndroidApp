package com.example.mihai.getmydrivercardapp;

import com.example.mihai.getmydrivercardapp.daggerconfig.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class GetMyDriverCardApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();    }
}
