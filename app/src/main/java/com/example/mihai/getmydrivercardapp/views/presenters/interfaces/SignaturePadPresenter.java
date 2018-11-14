package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.SignaturePadView;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

public interface SignaturePadPresenter {
    void subscribe(SignaturePadView view);

    void saveUser(User user, CardApplication cardApplication);
    void saveImages (User user);
    void assignSignature(Bitmap bitmapImage, CardApplication cardApplication);
    void assignDateOfSubmission(CardApplication cardApplication);
    void validate();
    void setValidator(Validator validator);

    void handleOnValidationFailed(List<ValidationError> errors);
}
