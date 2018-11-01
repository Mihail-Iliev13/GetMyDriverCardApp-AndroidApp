package com.example.mihai.getmydrivercardapp;

import android.app.Activity;

public interface Navigator {
    void navigateTo(Class<? extends Activity> to);
}
