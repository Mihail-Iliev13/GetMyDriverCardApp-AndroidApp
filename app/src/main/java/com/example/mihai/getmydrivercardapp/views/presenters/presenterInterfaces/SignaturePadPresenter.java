package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface SignaturePadPresenter extends BasePresenter{
    void setValueToSignature(CardApplication mCardApplication, byte[] byteImage);
    void saveUser(String email, CardApplication cardApplication);
    void assignApplicationSignatureValue(Bitmap bitmapImage);
}
