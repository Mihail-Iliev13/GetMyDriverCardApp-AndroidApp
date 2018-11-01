package com.example.mihai.getmydrivercardapp.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class BitmapConverterImpl implements BitmapConverter {

    @Override
    public byte[] toByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }
}
