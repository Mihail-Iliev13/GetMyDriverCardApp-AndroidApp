package com.example.mihai.getmydrivercardapp.utils.bitmapconverter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mihai.getmydrivercardapp.utils.bitmapconverter.base.BitmapConverter;

import java.io.ByteArrayOutputStream;

public class BitmapConverterImpl implements BitmapConverter {

    @Override
    public byte[] toByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, baos);
        return baos.toByteArray();
    }

    @Override
    public Bitmap toBitMap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
