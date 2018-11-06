package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.content.Intent;

import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;

interface Navigable {
    void setNavigator(Navigator navigator);
    void navigate();
    Intent prepareIntent();
}
