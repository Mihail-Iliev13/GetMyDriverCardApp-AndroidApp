package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import android.content.Intent;

import com.example.mihai.getmydrivercardapp.Navigator;

interface Navigable {
    void setNavigator(Navigator navigator);
    void navigate();
    Intent prepareIntent();
}
