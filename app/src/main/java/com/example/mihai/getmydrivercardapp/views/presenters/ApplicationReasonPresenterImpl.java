package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.base.ApplicationReasonConverter;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ApplicationReasonView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationReasonPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class ApplicationReasonPresenterImpl implements ApplicationReasonPresenter {

    private static final int FIRST_RADIO_BUTTON_ID = 2131296456;
    private static final int SECOND_RADIO_BUTTON_ID = 2131296455;
    private static final int THIRD_RADIO_BUTTON_ID = 2131296458;
    private static final int FOURTH_RADIO_BUTTON_ID = 2131296457;

    private ApplicationReasonView mApplicationReasonView;
    private ApplicationReasonConverter mConverter;
    private UserService mUserService;
    private AsyncRunner mAsyncRunner;

    @Inject
    public ApplicationReasonPresenterImpl (ApplicationReasonConverter converter,
                                           UserService userService,
                                           AsyncRunner asyncRunner) {
        this.mConverter = converter;
        this.mUserService = userService;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void subscribe(ApplicationReasonView view) {
            this.mApplicationReasonView = view;
    }

    @Override
    public void handleOnCheckedChange(int id) {
        switch (id) {
            case FIRST_RADIO_BUTTON_ID:
                mApplicationReasonView
                        .setCardApplicationReason(CardApplicationReason.NEW_CARD);
                mApplicationReasonView
                        .navigate();
                return;
            case SECOND_RADIO_BUTTON_ID:
                mApplicationReasonView
                        .setCardApplicationReason(CardApplicationReason.EXCHANGE);
                mApplicationReasonView
                        .navigate();
                return;
            case THIRD_RADIO_BUTTON_ID:
                mApplicationReasonView.showDialog(
                        "I want to replace my current card because:",
                        R.array.replacement_reasons);
                return;
            case FOURTH_RADIO_BUTTON_ID:
                mApplicationReasonView.showDialog(
                        "I want to renew my current card because:",
                        R.array.renewal_reasons);
                return;
                default:
                    break;
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
