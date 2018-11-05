package com.example.mihai.getmydrivercardapp.views.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.StringConstants;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.views.activities.CardApplicationDetailsActivity;
import com.example.mihai.getmydrivercardapp.views.adapters.CardApplicationArrayAdapter;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardApplicationListFragment extends Fragment implements CardApplicationListView {

    @BindView(R.id.lv_applications) ListView mListView;
    private Button mChangeStatusButton;
    private TextView mStatusText;

    private CardApplicationArrayAdapter mAdapter;
    private CardApplicationListPresenter mCardApplicationListPresenter;
    private CardApplication mSelectedCardApplication;

    @Inject
    public CardApplicationListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_application_list, container, false);

        ButterKnife.bind(this, view);


        mAdapter = new CardApplicationArrayAdapter(Objects.requireNonNull(getContext()),
                R.layout.custom_list_item);

        mAdapter.setCardApplicationListView(this);


        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener((parent, view1, position, id) -> {
            CardApplication selectedCardApplication = mAdapter.getItem(position);
            mCardApplicationListPresenter.selectCardApplication(selectedCardApplication);
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCardApplicationListPresenter
                .loadCardApplications();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if ( presenter instanceof CardApplicationListPresenter) {
            this.mCardApplicationListPresenter = (CardApplicationListPresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void showApplications(List<CardApplication> cardApplications) {
        Objects.requireNonNull(getActivity())
                .runOnUiThread(() -> {
            mAdapter.clear();
            mAdapter.addAll(cardApplications);
        });
    }

    @Override
    public void showEmptyListMessage() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            Toast.makeText(getContext(),
                    "The list is empty!",
                    Toast.LENGTH_SHORT)
                    .show();
        });
    }

    @Override
    public void navigateToCardApplicationDetails(CardApplication selectedCardApplication) {
        Intent intent = new Intent(getContext(), CardApplicationDetailsActivity.class);
        intent.putExtra(StringConstants.CARD_APPLICATION_KEY, selectedCardApplication);
        startActivity(intent);
    }


    @Override
    public void showError(Exception e) {

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            Toast.makeText(getContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_SHORT)
                    .show();
        });
    }

    @Override
    public AlertDialog.Builder buildStatusDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Change status: ");
        String[] statusValues = CardApplicationStatus.stringValues();

        builder.setSingleChoiceItems(statusValues, -1, (dialog, index) -> {
            String status = statusValues[index];
            mCardApplicationListPresenter.updateApplicationStatus(status);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {

        });

        return builder;
    }

    @Override
    public void showStatusDialog() {
        AlertDialog.Builder builder = buildStatusDialog();
        AlertDialog alertDialog = builder.create();

        Objects.requireNonNull(getActivity())
                .runOnUiThread(alertDialog::show);
    }

    @Override
    public void setSelectedCardApplication(CardApplication selectedCardApplication) {
        this.mSelectedCardApplication = selectedCardApplication;
    }
}
