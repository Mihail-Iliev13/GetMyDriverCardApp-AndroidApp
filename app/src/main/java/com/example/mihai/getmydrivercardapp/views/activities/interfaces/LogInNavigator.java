package com.example.mihai.getmydrivercardapp.views.activities.interfaces;

import android.content.Intent;

public interface LogInNavigator extends Navigator {
    void navigateToApplicationStatus(Intent intent);
    void navigateToApplicationsList(Intent intent);
    void navigateToApplicationReason(Intent intent);
}
