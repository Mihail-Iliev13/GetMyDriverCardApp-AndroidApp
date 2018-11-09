package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.base.ApplicationReasonConverter;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ApplicationReasonView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationReasonPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class ApplicationReasonPresenterImpl implements ApplicationReasonPresenter {

    private ApplicationReasonView mApplicationReasonView;
    private ApplicationReasonConverter mConverter;

    @Inject
    public ApplicationReasonPresenterImpl (ApplicationReasonConverter converter) {
        this.mConverter = converter;
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
            case 1:
                mApplicationReasonView
                        .setCardApplicationReason(CardApplicationReason.NEW_CARD);
                mApplicationReasonView
                        .navigate();
                return;
            case 2:
                mApplicationReasonView
                        .setCardApplicationReason(CardApplicationReason.EXCHANGE);
                mApplicationReasonView
                        .navigate();
                return;
            case 3:
                mApplicationReasonView.showDialog("I want to replace my current card because:",
                        R.array.replacement_reasons);
                return;
            case 4:
                mApplicationReasonView.showDialog("I want to renew my current card because:",
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

//            if (reason == CardApplicationReason.STOLEN
//                    || reason == CardApplicationReason.LOST) {
//                mApplicationReasonView.navigate();
//                return;
//            }
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }


    }
}
