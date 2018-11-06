package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public interface SearchToolBarView extends BaseView, DatePickerView {
    Toolbar getToolbar();
    MaterialSearchView getSearchView();
    void setSpinnerDropdownList();
    AlertDialog.Builder buildStatusDialog();
    void setSpinnerSelectedItemToDefaultValue();
    void showStatusDialog();
}
