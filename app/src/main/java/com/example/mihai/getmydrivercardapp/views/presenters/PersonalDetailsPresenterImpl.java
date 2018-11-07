package com.example.mihai.getmydrivercardapp.views.presenters;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

import butterknife.BindView;

public class PersonalDetailsPresenterImpl implements PersonalDetailsPresenter {
 private PersonalDetailsView mPersonalDetailsView;

    @BindView(R.id.rl_reason_lost)
    RelativeLayout mLostElements;
    @BindView(R.id.rl_reason_exchange)
    RelativeLayout mExchangeElements;
    @BindView(R.id.rl_reason_renewal)
    RelativeLayout mRenewalElements;

 @Inject
 public PersonalDetailsPresenterImpl(){
     //
    }

    @Override
    public void subscribe(BaseView view) {
        if(view instanceof PersonalDetailsView) {
            this.mPersonalDetailsView = (PersonalDetailsView) view;
        }
        else throw new InvalidParameterException();
    }

    @Override
    public void handleOnClickNext() {
        mPersonalDetailsView.setCardApplicationFields();
        mPersonalDetailsView.navigate();
    }

    @Override
    public void handleOnClickPickDateButton() {
        (mPersonalDetailsView).showDatePicker();
    }

    @Override
    public void CheckReasonAndRevealElementsIfNeeded(CardApplication cardApp) {

        if(cardApp.getCardApplicationReason()== CardApplicationReason.LOST){
            mLostElements.setVisibility(View.VISIBLE);

            }
        else if(cardApp.getCardApplicationReason()== CardApplicationReason.EXCHANGE){
            mExchangeElements.setVisibility(View.VISIBLE);
        }
        else if(cardApp.getCardApplicationReason()== CardApplicationReason.EXPIRED){
            mRenewalElements.setVisibility(View.VISIBLE);
        }
        else ;
    }


}
