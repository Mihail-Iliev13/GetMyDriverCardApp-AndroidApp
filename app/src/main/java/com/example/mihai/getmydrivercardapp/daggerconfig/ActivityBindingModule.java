package com.example.mihai.getmydrivercardapp.daggerconfig;


import com.example.mihai.getmydrivercardapp.views.activities.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped()
    @ContributesAndroidInjector(modules = ServiceModule.class)
    abstract LoginActivity loginActivity();

//    @ActivityScoped
//    @ContributesAndroidInjector(modules = TaskDetailPresenterModule.class)
//    abstract TaskDetailActivity taskDetailActivity();
}