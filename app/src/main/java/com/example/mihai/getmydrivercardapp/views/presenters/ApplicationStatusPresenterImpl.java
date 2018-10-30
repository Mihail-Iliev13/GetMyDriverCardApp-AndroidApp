package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.models.enums.CardAppStatus;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ApplicationStatusView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ApplicationStatusPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class ApplicationStatusPresenterImpl implements ApplicationStatusPresenter {

    private ApplicationStatusView mApplicationStatusView;

    @Inject
    public ApplicationStatusPresenterImpl () {

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
    public void showStatusMessage(CardAppStatus status) {
        String message;
        switch (status){
            case NEW:
                message = "Your application has been sent but is not approved yet! " +
                        "You will receive email when your application is approved/rejected";
                break;
            case APPROVED:
                message = "Your application has been approved! You will receive email when your card is ready";
                break;
            case REJECTED:
                message = "Your application has been rejected!";
                break;
            default:
                throw new InvalidParameterException();
        }
        mApplicationStatusView.setMessageToTextView(message);
    }
}
