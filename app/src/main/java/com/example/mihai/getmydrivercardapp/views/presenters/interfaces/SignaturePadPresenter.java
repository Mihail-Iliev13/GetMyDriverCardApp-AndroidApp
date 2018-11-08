package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.mobsandgeeks.saripaar.Validator;

public interface SignaturePadPresenter extends BasePresenter{
    void saveUser(User user, CardApplication cardApplication);
    void saveImages (User user, CardApplication cardApplication);
    void assignSignature(Bitmap bitmapImage, CardApplication cardApplication);
    void assignDateOfSubmission(CardApplication cardApplication);

    void validate();

    void setValidator(Validator validator);
}
