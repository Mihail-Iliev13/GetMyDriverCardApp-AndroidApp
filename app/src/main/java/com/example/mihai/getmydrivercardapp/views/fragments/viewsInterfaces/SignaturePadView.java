package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

public interface SignaturePadView extends BaseView, ErrorView, CardApplicationTransferable {
    void setSignature(byte[] byteArrayImage);
}
