package com.example.mihai.getmydrivercardapp.daggerconfig.presentersModules;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.services.Base.Service;
import com.example.mihai.getmydrivercardapp.views.presenters.LogInPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.LogInPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LogInPresenterModule {
    @Provides
    public LogInPresenter userService(Service service, AsyncRunner asyncRunner) {
        return new LogInPresenterImpl(service, asyncRunner);
    }
}