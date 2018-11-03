package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.services.imageservice.base.ImageService;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.utils.BitmapConverter;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.base.ApplicationReasonConverter;
import com.example.mihai.getmydrivercardapp.views.presenters.ApplicationReasonPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.ApplicationStatusPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.CardApplicationDetailsPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.CardApplicationListPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.ContactDetailsPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.ImageCapturePresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.LogInPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.PersonalDetailsPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.SearchToolBarPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.SignaturePadPresenterImpl;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ApplicationReasonPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ApplicationStatusPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationDetailsPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ContactDetailsPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ImageCapturePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.LogInPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.PersonalDetailsPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SearchToolBarPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SignaturePadPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterBindingModule {
    @Provides
    public ImageCapturePresenter imageCapturePresenter(BitmapConverter bitmapConverter) {
        return new ImageCapturePresenterImpl(bitmapConverter);
    }

    @Provides
    public LogInPresenter userService(UserService userService, AsyncRunner asyncRunner) {
        return new LogInPresenterImpl(userService, asyncRunner);
    }

    @Provides
    public SignaturePadPresenter signaturePadPresenter(UserService userService, ImageService imageService,
                                                       AsyncRunner asyncRunner, BitmapConverter bitmapConverter) {
        return new SignaturePadPresenterImpl(userService, imageService, asyncRunner, bitmapConverter);
    }

    @Provides
    public CardApplicationListPresenter cardApplicationListPresenter
            (CardApplicationService cardApplicationService, AsyncRunner asyncRunner) {
        return new CardApplicationListPresenterImpl(cardApplicationService, asyncRunner);
    }

    @Provides
    public SearchToolBarPresenter searchToolBarPresenter(UserService userService, AsyncRunner asyncRunner) {
        return new SearchToolBarPresenterImpl(userService, asyncRunner);
    }

    @Provides
    public ApplicationStatusPresenter applicationStatusPresenter() {
        return new ApplicationStatusPresenterImpl();
    }

    @Provides
    public CardApplicationDetailsPresenter applicationDetailsPresenter(BitmapConverter bitmapConverter) {
        return new CardApplicationDetailsPresenterImpl(bitmapConverter);
    }

    @Provides
    public ApplicationReasonPresenter applicationReasonPresenter(ApplicationReasonConverter applicationReasonConverter) {
        return new ApplicationReasonPresenterImpl(applicationReasonConverter);
    }

    @Provides
    public PersonalDetailsPresenter personalDetailsPresenter() {
        return new PersonalDetailsPresenterImpl();
    }

    @Provides
    public ContactDetailsPresenter contactDetailsPresenter() {
        return new ContactDetailsPresenterImpl();
    }
}

