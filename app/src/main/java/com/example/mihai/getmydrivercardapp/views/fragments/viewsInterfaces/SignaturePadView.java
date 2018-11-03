package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import com.example.mihai.getmydrivercardapp.models.ImageModel;

import java.util.ArrayList;

public interface SignaturePadView extends BaseView, ErrorView, CardApplicationTransferable {
    void setSignature(byte[] byteArrayImage);
    void setImages(ArrayList<ImageModel> images);
}
