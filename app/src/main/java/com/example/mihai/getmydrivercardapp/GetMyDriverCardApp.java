package com.example.mihai.getmydrivercardapp;

import com.example.mihai.getmydrivercardapp.customannotations.bgphoneprefix.BGPhoneNumber;
import com.example.mihai.getmydrivercardapp.customannotations.dateformat.DateFormat;
import com.example.mihai.getmydrivercardapp.customannotations.latincharacters.LatinCharacters;
import com.example.mihai.getmydrivercardapp.daggerconfig.DaggerAppComponent;
import com.mobsandgeeks.saripaar.Validator;


import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class GetMyDriverCardApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        Validator.registerAnnotation(LatinCharacters.class);
        Validator.registerAnnotation(BGPhoneNumber.class);
        Validator.registerAnnotation(DateFormat.class);
        return DaggerAppComponent.builder().application(this).build();    }
}
