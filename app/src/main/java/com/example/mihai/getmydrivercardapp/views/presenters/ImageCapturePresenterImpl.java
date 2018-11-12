package com.example.mihai.getmydrivercardapp.views.presenters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;

import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.utils.bitmapconverter.base.BitmapConverter;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ImageCaptureView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.ImageCapturePresenter;

import java.io.File;
import java.security.InvalidParameterException;

import javax.inject.Inject;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.EasyImageConfig;

public class ImageCapturePresenterImpl implements ImageCapturePresenter {

    private ImageCaptureView mImageCaptureView;
    private BitmapConverter mBitmapConverter;

    @Inject
    public ImageCapturePresenterImpl(BitmapConverter bitmapConverter) {
        this.mBitmapConverter = bitmapConverter;
    }

    @Override
    public void openCamera(Fragment fragment) {
        EasyImage.openCamera(fragment, EasyImageConfig.REQ_TAKE_PICTURE);
    }

    @Override
    public void handleActivityResult(int requestCode, int resultCode, Intent data, Activity activity) {
        EasyImage.handleActivityResult
                (requestCode, resultCode, data, activity, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                String path = imageFile.getPath();
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                mImageCaptureView.setImageBitmap(bitmap);
            }
        });
    }


    @Override
    public void setValueToImage(ImageModel imageModel,
                                byte[] byteImage) {
        imageModel.setImage(byteImage);
    }

    @Override
    public void handleOnProceedClick(Bitmap bitmap, ImageModel imageModel) {
        byte[] byteArray = mBitmapConverter.toByteArray(bitmap);
        setValueToImage(imageModel, byteArray);
        mImageCaptureView.navigate();
    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof ImageCaptureView) {
            this.mImageCaptureView = (ImageCaptureView) view;
        } else {
            throw new InvalidParameterException();
        }
    }
}
