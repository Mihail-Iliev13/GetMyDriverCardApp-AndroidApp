package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;

import java.util.List;

public interface SignaturePadPresenter extends BasePresenter{
    void setValueToSignature(CardApplication mCardApplication, byte[] byteImage);
    void saveUser(String email, CardApplication cardApplication);

    void saveImages (String email, List<ImageModel> images);

    void assignSignature(Bitmap bitmapImage, CardApplication mCardApplication);

    void assignDateOfSubmission(CardApplication mCardApplication);
}
