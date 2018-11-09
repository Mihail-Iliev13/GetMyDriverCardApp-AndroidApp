package com.example.mihai.getmydrivercardapp.views.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ApplicationReasonView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ApplicationReasonPresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.BasePresenter;
import com.mobsandgeeks.saripaar.annotation.Checked;

import java.security.InvalidParameterException;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicationReasonFragment extends Fragment implements ApplicationReasonView {

    @BindView(R.id.radio_group)
    @Checked
    RadioGroup mRadioGroup;

    private ApplicationReasonPresenter mApplicationReasonPresenter;
    private CardApplication mCardApplication;
    private User mUser;
    private Navigator mNavigator;
    private int mIndex;

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
    public void onStart() {
        super.onStart();
        mRadioGroup.clearCheck();
        mIndex = -1;
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
        intent.putExtra(IntentKeys.CARD_APPLICATION_KEY, mCardApplication);
        intent.putExtra(IntentKeys.USER_KEY, mUser);
        return intent;
    }

    @Override
    public void showDialog(String title, int resourceID) {
        AlertDialog.Builder builder = buildDialog(title, resourceID);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialog1 -> {
            Button button = ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> {
                String[] values = Objects.requireNonNull(getActivity())
                        .getResources()
                        .getStringArray(resourceID);

                if (mIndex == -1) {
                    Toast.makeText(getContext(),
                            "Choose an option to proceed",
                            Toast.LENGTH_LONG).show();
                } else {
                    String reason = values[mIndex];
                    mApplicationReasonPresenter
                            .handleDialogPositiveButtonOnclick(reason, mCardApplication);
                    dialog1.dismiss();
                }
            });
        });
        dialog.setOnDismissListener(dialog12 -> mRadioGroup.clearCheck());
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

        builder.setSingleChoiceItems(values, -1, (dialog, index) -> {
            mIndex  = index;
        });


        builder.setPositiveButton("OK",  null);
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
