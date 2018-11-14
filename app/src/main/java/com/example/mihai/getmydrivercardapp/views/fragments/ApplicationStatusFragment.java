package com.example.mihai.getmydrivercardapp.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ApplicationStatusView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationStatusPresenter;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ApplicationStatusFragment extends Fragment implements ApplicationStatusView{

    @BindView(R.id.tv_message) TextView mStatusTextView;
    @BindView(R.id.iv_status_image) ImageView mStatusImage;
    @BindView(R.id.pb_loading) ProgressBar mProgressBar;
    @BindView(R.id.btn_log_out) Button mLogOut;

    private ApplicationStatusPresenter mApplicationStatusPresenter;
    private User mUser;
    private Navigator mNavigator;

    @Inject
    public ApplicationStatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_status, container, false);
        ButterKnife.bind(this, view);
        mLogOut.setOnClickListener(v -> mApplicationStatusPresenter.logOut());
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mApplicationStatusPresenter
                .loadStatusMessage(mUser);
    }

    @Override
    public void setPresenter(ApplicationStatusPresenter presenter) {
            this.mApplicationStatusPresenter = presenter;
    }

    @Override
    public void showStatus(String message, int drawableResource) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mStatusImage.setImageResource(drawableResource);
            mStatusTextView.setText(message);
        });
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void showLoading() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mStatusTextView.setVisibility(View.GONE);
            mStatusImage.setVisibility(View.GONE);
            mLogOut.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mProgressBar.setVisibility(View.GONE);
            mStatusTextView.setVisibility(View.VISIBLE);
            mStatusImage.setVisibility(View.VISIBLE);
            mLogOut.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void navigate() {
        Intent intent = prepareIntent();
        mNavigator.navigateWith(intent);
    }

    @Override
    public Intent prepareIntent() {
        return new Intent();
    }
}
