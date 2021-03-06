package com.example.mihai.getmydrivercardapp.views.fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.enums.FilterCriteria;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.SearchToolBarView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SearchToolBarPresenter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchToolBarFragment extends Fragment implements SearchToolBarView,
        DatePickerDialog.OnDateSetListener {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.spinner)
    Spinner mSpinner;

    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;

    private SearchToolBarPresenter mSearchToolBarPresenter;

    @Inject
    public SearchToolBarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_tool_bar, container, false);
        ButterKnife.bind(this, view);

        mToolbar.setTitle("Search by: ");

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FilterCriteria filterCriteria = (FilterCriteria) mSpinner.getSelectedItem();
                mSearchToolBarPresenter.handleSpinnerOnItemSelected(filterCriteria);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String pattern) {
                FilterCriteria filterCriteria = (FilterCriteria) mSpinner.getSelectedItem();
                try {
                    mSearchToolBarPresenter
                            .getFilteredCardApplications(pattern, filterCriteria);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String pattern) {
                FilterCriteria filterCriteria = (FilterCriteria) mSpinner.getSelectedItem();
                try {
                    mSearchToolBarPresenter
                            .getFilteredCardApplications(pattern, filterCriteria);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSearchToolBarPresenter.setFilterValues();
    }

    @Override
    public void setPresenter(SearchToolBarPresenter presenter) {
            this.mSearchToolBarPresenter = presenter;
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public MaterialSearchView getSearchView() {
        return mSearchView;
    }

    @Override
    public void setSpinnerDropdownList() {
        ArrayAdapter<FilterCriteria> filterValuesAdapter =
                new ArrayAdapter<>(Objects.requireNonNull(getActivity()),
                R.layout.custom_spinner_item,
                FilterCriteria.values());

        filterValuesAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(filterValuesAdapter);
    }

    @Override
    public void setSpinnerSelectedItemToDefaultValue() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mSpinner.setSelection(0);
        });
    }

    @Override
    public DatePickerDialog initializeDatePickerDialog(int year, int month, int day) {
        return new DatePickerDialog(
                Objects.requireNonNull(getContext()),
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                this,
                year, month, day);
    }

    @Override
    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = initializeDatePickerDialog(year, month, day);
        datePickerDialog.setOnCancelListener(dialog -> {
           setSpinnerSelectedItemToDefaultValue();
        });

        Objects.requireNonNull(getActivity())
                .runOnUiThread(datePickerDialog::show);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        try {
            FilterCriteria filterCriteria = (FilterCriteria) mSpinner.getSelectedItem();
            String dateString = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
            mSearchToolBarPresenter
                    .getFilteredCardApplications(dateString, filterCriteria);
            setSpinnerSelectedItemToDefaultValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AlertDialog.Builder buildStatusDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select status: ");
        String[] statusValues = CardApplicationStatus.stringValues();

        builder.setSingleChoiceItems(statusValues, -1, (dialog, index) -> {
            try {
                FilterCriteria filterCriteria = (FilterCriteria) mSpinner.getSelectedItem();
                String status = statusValues[index];
                mSearchToolBarPresenter.getFilteredCardApplications(status, filterCriteria);
                setSpinnerSelectedItemToDefaultValue();
                dialog.dismiss();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            setSpinnerSelectedItemToDefaultValue();
        });

        builder.setOnCancelListener(dialog -> {
            if (mSearchView.isSearchOpen()) {
                mSearchView.closeSearch(); }
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
}
