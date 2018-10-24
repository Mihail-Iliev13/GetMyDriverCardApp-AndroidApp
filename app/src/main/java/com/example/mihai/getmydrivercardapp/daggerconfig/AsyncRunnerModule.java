package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.async.AsyncRunnerImpl;
import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AsyncRunnerModule {
    @Provides
    @Singleton
    public AsyncRunner asyncRunner() {
        return new AsyncRunnerImpl();
    }
}