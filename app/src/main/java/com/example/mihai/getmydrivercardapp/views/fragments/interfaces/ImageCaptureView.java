package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ImageCapturePresenter;

public interface ImageCaptureView extends CardApplicationTransferable, Navigable {
    void showImage(Bitmap bitmap);
    void setImageModel(ImageModel image);
    void setInstructionMessage(String message);
    void setPresenter(ImageCapturePresenter presenter);
}
