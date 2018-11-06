package com.example.mihai.getmydrivercardapp.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.OptionsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.OptionsPresenter;


import java.security.InvalidParameterException;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsFragment extends Fragment implements OptionsView{

    @BindView(R.id.rb_reason1)
    RadioButton mApplyForFirstTime;
    @BindView(R.id.rb_reason2)
    RadioButton mExchangeEUcard;
    @BindView(R.id.rb_reason3)
    RadioButton mReplacementDueToLost;
    @BindView(R.id.rb_reason4)
    RadioButton mReplacementDueToChange;
    @BindView(R.id.rb_reason5)
    RadioButton mRenewal;

    private OptionsPresenter mOptionsPresenter;
    private CardApplication mCardApplication;
    private User mUser;

    @Inject
    public OptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    @Override
    public void setUser(User user) {
        this.mUser=user;
    }

    @Override
    public void CardApplication(CardApplication cardApplication) {
this.mCardApplication=cardApplication;
    }

    @Override
    public void navigateToNextView() {

    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof  OptionsPresenter) {
            mOptionsPresenter= (OptionsPresenter) presenter;
        }
        else throw new InvalidParameterException();
    }
}
