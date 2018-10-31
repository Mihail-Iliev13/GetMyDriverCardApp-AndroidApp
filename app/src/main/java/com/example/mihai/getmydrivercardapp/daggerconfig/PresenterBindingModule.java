package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.services.Base.Service;
import com.example.mihai.getmydrivercardapp.views.presenters.ApplicationStatusPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.CardApplicationDetailsPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.CardApplicationListPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.ImageCapturePresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.LogInPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.SearchToolBarPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.SignaturePadPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ApplicationStatusPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationDetailsPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ImageCapturePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.LogInPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SearchToolBarPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SignaturePadPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterBindingModule {
    @Provides
    public ImageCapturePresenter imageCapturePresenter() {
        return new ImageCapturePresenterImpl();
    }

    @Provides
    public LogInPresenter userService(Service service, AsyncRunner asyncRunner) {
        return new LogInPresenterImpl(service, asyncRunner);
    }

    @Provides
    public SignaturePadPresenter signaturePadPresenter(Service service, AsyncRunner asyncRunner) {
        return new SignaturePadPresenterImpl(service, asyncRunner);
    }

    @Provides
    public CardApplicationListPresenter cardApplicationListPresenter(Service service, AsyncRunner asyncRunner) {
        return new CardApplicationListPresenterImpl(service, asyncRunner);
    }

    @Provides
    public SearchToolBarPresenter searchToolBarPresenter(Service service, AsyncRunner asyncRunner) {
        return new SearchToolBarPresenterImpl(service, asyncRunner);
    }

    @Provides
    public ApplicationStatusPresenter applicationStatusPresenter() {
        return new ApplicationStatusPresenterImpl();
    }

    @Provides
    public CardApplicationDetailsPresenter applicationDetailsPresenter() {
        return new CardApplicationDetailsPresenterImpl();
    }
}
