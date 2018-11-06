package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.app.DatePickerDialog;

public interface DatePickerView {
    DatePickerDialog initializeDatePickerDialog(int year, int month, int day);
    void showDatePicker();
}
