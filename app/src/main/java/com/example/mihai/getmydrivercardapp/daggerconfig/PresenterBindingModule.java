package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.services.imageservice.base.ImageService;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.utils.bitmapconverter.base.BitmapConverter;
import com.example.mihai.getmydrivercardapp.utils.datehandler.base.DateHandler;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.base.ApplicationReasonConverter;
import com.example.mihai.getmydrivercardapp.utils.statusconverter.base.ApplicationStatusConverter;
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
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationReasonPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationStatusPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.CardApplicationDetailsPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.CardApplicationListPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ContactDetailsPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ImageCapturePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.LogInPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SearchToolBarPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SignaturePadPresenter;

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
                                                       AsyncRunner asyncRunner, BitmapConverter bitmapConverter,
                                                       DateHandler dateHandler) {
        return new SignaturePadPresenterImpl(userService, imageService, asyncRunner,bitmapConverter, dateHandler);
    }

    @Provides
    public CardApplicationListPresenter cardApplicationListPresenter
            (CardApplicationService cardApplicationService, AsyncRunner asyncRunner,
             ApplicationStatusConverter applicationStatusConverter) {
        return new CardApplicationListPresenterImpl(cardApplicationService,asyncRunner);
    }

    @Provides
    public SearchToolBarPresenter searchToolBarPresenter(CardApplicationService cardApplicationService,
                                                         AsyncRunner asyncRunner) {
        return new SearchToolBarPresenterImpl(cardApplicationService, asyncRunner);
    }

    @Provides
    public ApplicationStatusPresenter applicationStatusPresenter(UserService userService,
                                                                 AsyncRunner asyncRunner) {
        return new ApplicationStatusPresenterImpl(userService, asyncRunner);
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

