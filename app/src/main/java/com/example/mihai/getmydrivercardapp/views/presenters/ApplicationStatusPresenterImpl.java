package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.constants.StatusMessages;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ApplicationStatusView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationStatusPresenter;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.inject.Inject;

public class ApplicationStatusPresenterImpl implements ApplicationStatusPresenter {

    private ApplicationStatusView mApplicationStatusView;
    private UserService mUserService;
    private AsyncRunner mAsyncRunner;

    @Inject
    public ApplicationStatusPresenterImpl (UserService userService, AsyncRunner asyncRunner) {
        this.mUserService = userService;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof ApplicationStatusView){
            this.mApplicationStatusView = (ApplicationStatusView) view;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void loadStatusMessage(User user) {
        mApplicationStatusView.showLoading();
        mAsyncRunner.runInBackground(() -> {
            CardApplication cardApplication = null;
            try {
                cardApplication = mUserService.getPendingApplication(user);
                CardApplicationStatus status = cardApplication.getStatus();

                String message;
                int drawable;
                switch (status){
                    case NEW:
                        drawable = R.drawable.icon_sent;
                        message = StatusMessages.NEW;
                        break;
                    case APPROVED:
                        drawable = R.drawable.icon_checkmark;
                        message = StatusMessages.APPROVED;
                        break;
                    case REJECTED:
                        drawable = R.drawable.icon_rejected;
                        message = StatusMessages.REJECTED;
                        break;
                    default:
                        throw new InvalidParameterException();
                }
                mApplicationStatusView.hideLoading();
                mApplicationStatusView.showStatus(message, drawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void logOut() {
        mApplicationStatusView.navigate();
    }
}
