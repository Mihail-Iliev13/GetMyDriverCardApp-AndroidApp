package com.example.mihai.getmydrivercardapp.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ApplicationStatusView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ApplicationStatusPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ApplicationStatusFragment extends Fragment implements ApplicationStatusView{

    @BindView(R.id.tv_message) TextView mStatusTextView;

    private CardApplication mCardApplication;
    private ApplicationStatusPresenter mApplicationStatusPresenter;

    @Inject
    public ApplicationStatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_status, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mApplicationStatusPresenter
                .showStatusMessage(mCardApplication.getStatus());
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof ApplicationStatusPresenter) {
            this.mApplicationStatusPresenter = (ApplicationStatusPresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void setMessageToTextView(String message) {
        mStatusTextView.setText(message);
    }

    @Override
    public void setCardApplication(CardApplication cardApplication) {
        this.mCardApplication = cardApplication;
    }
}
