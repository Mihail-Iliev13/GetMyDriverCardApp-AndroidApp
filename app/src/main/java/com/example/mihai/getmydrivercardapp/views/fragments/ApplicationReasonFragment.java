package com.example.mihai.getmydrivercardapp.views.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.mihai.getmydrivercardapp.Constants;
import com.example.mihai.getmydrivercardapp.Navigator;
import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ApplicationReasonView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ApplicationReasonPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;

import java.security.InvalidParameterException;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicationReasonFragment extends Fragment implements ApplicationReasonView {

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    private ApplicationReasonPresenter mApplicationReasonPresenter;
    private CardApplication mCardApplication;
    private User mUser;
    private Navigator mNavigator;

    @Inject
    public ApplicationReasonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_reason, container, false);
        ButterKnife.bind(this, view);
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) ->
                mApplicationReasonPresenter.handleOnCheckedChange(checkedId));
        return view;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof ApplicationReasonPresenter) {
            this.mApplicationReasonPresenter = (ApplicationReasonPresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void setCardApplicationReason(CardApplicationReason reason) {
        mCardApplication
                .setCardApplicationReason(reason);
    }

    @Override
    public void navigate() {
        Intent intent = prepareIntent();
        mNavigator.navigateWith(intent);
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public Intent prepareIntent() {
        Intent intent = new Intent();
        intent.putExtra(Constants.CARD_APPLICATION_KEY, mCardApplication);
        intent.putExtra(Constants.USER_KEY, mUser);
        return intent;
    }

    @Override
    public void showDialog(String title, int resourceID) {
        AlertDialog.Builder builder = buildDialog(title, resourceID);
        AlertDialog dialog = builder.create();
        Objects.requireNonNull(getActivity())
                .runOnUiThread(dialog::show);
    }

    @Override
    public AlertDialog.Builder buildDialog(String title, int resourceID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);

        String[] values = Objects.requireNonNull(getActivity())
                .getResources()
                .getStringArray(resourceID);

        final int[] arrayIndex = new int[1];
        builder.setSingleChoiceItems(values, -1, (dialog, index) -> {
            arrayIndex[0] = index;
        });

        builder.setPositiveButton("OK", (dialog, index) -> {
            String reason = values[arrayIndex[0]];
            mApplicationReasonPresenter
                    .handlePositiveButtonOnclick(reason, mCardApplication);
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
        });

        return builder;
    }

    @Override
    public void setCurrentUser(User user) {
        this.mUser = user;
    }

    @Override
    public void setCurrentCardApplication(CardApplication cardApplication) {
        this.mCardApplication = cardApplication;
    }

}
