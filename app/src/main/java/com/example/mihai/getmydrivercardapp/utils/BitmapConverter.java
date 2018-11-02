package com.example.mihai.getmydrivercardapp.utils;

import android.graphics.Bitmap;

public interface BitmapConverter {
    byte[] toByteArray(Bitmap bitmap);
    Bitmap toBitMap(byte[] byteArray);
}
