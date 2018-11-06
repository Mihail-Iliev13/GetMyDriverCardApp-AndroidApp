package com.example.mihai.getmydrivercardapp.utils.bitmapconverter.base;

import android.graphics.Bitmap;

public interface BitmapConverter {
    byte[] toByteArray(Bitmap bitmap);
    Bitmap toBitMap(byte[] byteArray);
}
