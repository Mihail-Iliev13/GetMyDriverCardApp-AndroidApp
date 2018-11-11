package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.base.ApplicationReasonConverter;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ApplicationReasonView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationReasonPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class ApplicationReasonPresenterImpl implements ApplicationReasonPresenter {

    private ApplicationReasonView mApplicationReasonView;
    private ApplicationReasonConverter mConverter;
    private CardApplicationService mCardApplicationService;
    private UserService mUserService;
    private AsyncRunner mAsyncRunner;

    @Inject
    public ApplicationReasonPresenterImpl (ApplicationReasonConverter converter,
                                           CardApplicationService cardApplicationService,
                                           UserService userService,
                                           AsyncRunner asyncRunner) {
        this.mConverter = converter;
        this.mCardApplicationService = cardApplicationService;
        this.mUserService = userService;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof  ApplicationReasonView) {
            this.mApplicationReasonView = (ApplicationReasonView) view;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void handleOnCheckedChange(int id) {
        switch (id) {
            case 2131296454:
                mApplicationReasonView
                        .setCardApplicationReason(CardApplicationReason.NEW_CARD);
                mApplicationReasonView
                        .navigate();
                return;
            case 2131296453:
                mApplicationReasonView
                        .setCardApplicationReason(CardApplicationReason.EXCHANGE);
                mApplicationReasonView
                        .navigate();
                return;
            case 2131296456:
                mApplicationReasonView.showDialog(
                        "I want to replace my current card because:",
                        R.array.replacement_reasons);
                return;
            case 2131296455:
                mApplicationReasonView.showDialog(
                        "I want to renew my current card because:",
                        R.array.renewal_reasons);
                return;
                default:
                    return;
        }
    }

    @Override
    public void handleDialogPositiveButtonOnclick(String reasonStr, CardApplication cardApplication) {
        try {
            CardApplicationReason reason = mConverter.fromString(reasonStr);
            cardApplication.setCardApplicationReason(reason);
            mApplicationReasonView.navigate();
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void checkUserReason(User user) {
        mAsyncRunner.runInBackground(() -> {
            try {
                if (!mUserService.getApplications(user)
                        .isEmpty()) {
                    mApplicationReasonView
                            .disableApplicationReasonOptionNew();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
