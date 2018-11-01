package com.example.mihai.getmydrivercardapp.views.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.mihai.getmydrivercardapp.Constants;
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
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mApplicationReasonPresenter.handleOnCheckedChange(checkedId);
            }
        });
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
    public void setCardApplicationReason(CardApplicationReason applicationCardApplicationReason) {
        mCardApplication.setCardApplicationReason(applicationCardApplicationReason);
    }

    @Override
    public void navigate(Class<? extends Activity> activity) {
        Intent intent = new Intent(getContext(), activity);
        intent.putExtra(Constants.CARD_APPLICATION_KEY, mCardApplication);
        intent.putExtra(Constants.USER_KEY, mUser);
        startActivity(intent);
    }

    @Override
    public void showDialog(String title, int resourceID) {
        AlertDialog.Builder builder = buildDialog(title, resourceID);
        AlertDialog dialog = builder.create();
        dialog.show();
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
            mApplicationReasonPresenter.handlePositiveButtonOnclick(reason, mCardApplication);
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
