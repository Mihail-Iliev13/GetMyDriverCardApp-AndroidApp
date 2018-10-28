package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

import com.example.mihai.getmydrivercardapp.ImageAttribute;
import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface ImageCapturePresenter extends BasePresenter {

    void openCamera(Fragment fragment);
    void handleActivityResult(int requestCode, int resultCode, Intent data,
                              Activity activity);
    byte[] convertBitmapToByteArray(Bitmap bitmap);
    void setValueToImageAttribute(CardApplication mCardApplication, ImageAttribute mImageAttribute, byte[] byteImage);
}
