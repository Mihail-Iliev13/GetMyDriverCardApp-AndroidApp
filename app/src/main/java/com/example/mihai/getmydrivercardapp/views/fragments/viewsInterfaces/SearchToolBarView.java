package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.widget.Toolbar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public interface SearchToolBarView extends BaseView {
    Toolbar getToolbar();
    MaterialSearchView getSearchView();
    void setSpinnerDropdownList();
    void showDatePicker();
    AlertDialog.Builder buildStatusDialog();
    void setSpinnerSelectedItemToDefaultValue();
    DatePickerDialog initializeDatePickerDialog(int year, int month, int day);
    void showStatusDialog();

}
