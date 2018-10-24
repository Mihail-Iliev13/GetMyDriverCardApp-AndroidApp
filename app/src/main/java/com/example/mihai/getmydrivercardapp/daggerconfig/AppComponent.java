package com.example.mihai.getmydrivercardapp.daggerconfig;

import android.app.Application;

import com.example.mihai.getmydrivercardapp.GetMyDriverCardApp;
import com.example.mihai.getmydrivercardapp.daggerconfig.fragmentmodules.LogInFragmentModule;
import com.example.mihai.getmydrivercardapp.daggerconfig.presentersModules.LogInPresenterModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        HttpRequesteModule.class,
        ParsersModule.class,
        RepositoriesModule.class,
        ServiceModule.class,
        LogInFragmentModule.class,
        AsyncRunnerModule.class,
        LogInPresenterModule.class})
public interface AppComponent extends AndroidInjector<GetMyDriverCardApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AndroidInjector<? extends DaggerApplication> build();
    }
}
