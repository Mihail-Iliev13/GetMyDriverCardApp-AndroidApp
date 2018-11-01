package com.example.mihai.getmydrivercardapp.views.presenters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;

import com.example.mihai.getmydrivercardapp.ImageAttribute;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.PersonalDetails;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.ImageCaptureView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.ImageCapturePresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.InvalidParameterException;

import javax.inject.Inject;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.EasyImageConfig;

public class ImageCapturePresenterImpl implements ImageCapturePresenter {

    private ImageCaptureView mImageCaptureView;

    @Inject
    public ImageCapturePresenterImpl() {

    }

    @Override
    public void openCamera(Fragment fragment) {
        EasyImage.openCamera(fragment, EasyImageConfig.REQ_TAKE_PICTURE);
    }

    @Override
    public void handleActivityResult(int requestCode, int resultCode, Intent data, Activity activity) {
        EasyImage.handleActivityResult(requestCode, resultCode, data, activity, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                String path = imageFile.getPath();
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                mImageCaptureView.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 1, baos);
        return  baos.toByteArray();
    }

    @Override
    public void setValueToImageAttribute(CardApplication cardApplication, ImageAttribute imageAttribute,
                                         byte[] byteImage) {

        PersonalDetails personalDetails = cardApplication.getDetails();
        personalDetails.setSelfie(byteImage);
        personalDetails.setIdCardImage(byteImage);
        personalDetails.setDrivingLicenseImage(byteImage);
//        switch (imageAttribute) {
//            case SELFIE_IMAGE:
//                personalDetails.setSelfie(byteImage);
//                return;
//            case ID_CARD_IMAGE:
//                personalDetails.setIdCardImage(byteImage);
//                return;
//            case OLD_CARD_IMAGE:
//                personalDetails.setPreviousCardImage(byteImage);
//                return;
//            case DRIVING_LICENSE_IMAGE:
//                personalDetails.setDrivingLicenseImage(byteImage);
//                return;
//                default:
//                    throw new IllegalArgumentException();
//        }
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
