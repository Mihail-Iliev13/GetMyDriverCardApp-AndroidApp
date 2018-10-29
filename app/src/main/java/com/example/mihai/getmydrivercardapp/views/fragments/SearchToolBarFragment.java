package com.example.mihai.getmydrivercardapp.views.fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.SearchToolBarView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SearchToolBarPresenter;
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

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) mSpinner.getSelectedItem();
                mSearchToolBarPresenter.handleSpinnerOnItemSelected(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String pattern) {
                String criteria = (String) mSpinner.getSelectedItem();
                try {
                    mSearchToolBarPresenter.getFilteredCardApplications(pattern, criteria);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String pattern) {
                return false;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSearchToolBarPresenter.setFilterCriteria();
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
        ArrayAdapter<String> filterCriteriaAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.custom_spinner_item,
                getResources().getStringArray(R.array.filter_citeria));
        filterCriteriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(filterCriteriaAdapter);
    }

    @Override
    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = initializeDatePickerDialog(year, month, day);

        datePickerDialog.show();

        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                setSpinnerSelectedItemToDefaultValue();
            }
        });
    }

    @Override
    public DatePickerDialog initializeDatePickerDialog(int year, int month, int day) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Objects.requireNonNull(getContext()),
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                this,
                year, month, day);
        return datePickerDialog;
    }

    @Override
    public void showStatusDialog() {
        AlertDialog.Builder builder = buildStatusDialog();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public AlertDialog.Builder buildStatusDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select status: ");
        String[] statusValues = Objects.requireNonNull(getActivity())
                .getResources()
                .getStringArray(R.array.status);

        builder.setSingleChoiceItems(statusValues, -1, (dialog, index) -> {
            try {
                String criteria = (String) mSpinner.getSelectedItem();
                String status = statusValues[index];
                mSearchToolBarPresenter.getFilteredCardApplications(status, criteria);
                setSpinnerSelectedItemToDefaultValue();
                dialog.dismiss();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            setSpinnerSelectedItemToDefaultValue();
        });
        builder.setOnCancelListener(dialog -> setSpinnerSelectedItemToDefaultValue());
        return builder;
    }

    @Override
    public void setSpinnerSelectedItemToDefaultValue() {
        mSpinner.setSelection(0);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof SearchToolBarPresenter) {
            this.mSearchToolBarPresenter = (SearchToolBarPresenter) presenter;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        try {
            String criteria = (String) mSpinner.getSelectedItem();
            @SuppressLint("DefaultLocale")
            String dateString = String.format("%d/%d/%d", year, month, dayOfMonth);
            setSpinnerSelectedItemToDefaultValue();
            mSearchToolBarPresenter.getFilteredCardApplications(dateString, criteria);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
