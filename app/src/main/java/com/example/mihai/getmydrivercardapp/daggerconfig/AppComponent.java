package com.example.mihai.getmydrivercardapp.daggerconfig;

import android.app.Application;

import com.example.mihai.getmydrivercardapp.GetMyDriverCardApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        AndroidSupportInjectionModule.class,
        HttpRequesteModule.class,
        ParsersModule.class,
        RepositoriesModule.class,
        ServiceModule.class,
        AsyncRunnerModule.class,
        PresenterBindingModule.class,
        UtillitiesBindingModule.class})

public interface AppComponent extends AndroidInjector<GetMyDriverCardApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AndroidInjector<? extends DaggerApplication> build();
    }
}
