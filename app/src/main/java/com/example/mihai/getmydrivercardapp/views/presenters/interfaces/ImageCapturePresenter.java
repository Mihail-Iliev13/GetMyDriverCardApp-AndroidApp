package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ImageCaptureView;

public interface ImageCapturePresenter  {

    void openCamera(Fragment fragment);
    void handleActivityResult(int requestCode, int resultCode, Intent data,
                              Activity activity);

    void setValueToImage(ImageModel imageModel,
                         byte[] byteImage);

    void handleOnProceedClick(Bitmap bitmap, ImageModel imageModel);

    void subscribe(ImageCaptureView view);
}
