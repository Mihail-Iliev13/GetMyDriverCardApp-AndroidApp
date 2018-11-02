package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.ImageAttribute;

public interface ImageCaptureView extends BaseView, CardApplicationTransferable, Navigable {
    void setImageBitmap(Bitmap bitmap);
    void setImageAttribute(ImageAttribute imageAttribute);
}
