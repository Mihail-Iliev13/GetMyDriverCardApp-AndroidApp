package com.example.mihai.getmydrivercardapp.utils;

import com.example.mihai.getmydrivercardapp.enums.ImageAttribute;
import com.example.mihai.getmydrivercardapp.models.ImageModel;

public class ImageHolder {
    private static ImageModel SELFIE;
    private static ImageModel IDCARD;
    private static ImageModel DRIVINGLICENSE;
    private static ImageModel OLDCARD;

    public static void setDrivingLicense(ImageModel drivingLicense) {
        if (drivingLicense.getImageAttribute()
                .equals(ImageAttribute.DRIVING_LICENSE_IMAGE)) {
            ImageHolder.DRIVINGLICENSE = drivingLicense;
        }
    }

    public static void setIDCard(ImageModel image) {
        if (image.getImageAttribute()
                .equals(ImageAttribute.ID_CARD_IMAGE)) {
            ImageHolder.IDCARD = image;
        }
    }

    public static void setSelfie(ImageModel image) {
        if (image.getImageAttribute()
                .equals(ImageAttribute.SELFIE_IMAGE)) {
            ImageHolder.SELFIE = image;
        }
    }

    public static void setOldcard(ImageModel image) {
        if (image.getImageAttribute()
                .equals(ImageAttribute.OLD_CARD_IMAGE)) {
            ImageHolder.OLDCARD = image;
        }
    }

    public static ImageModel getSelfie() {
        return SELFIE;
    }

    public static ImageModel getIdCard() {
        return IDCARD;
    }

    public static ImageModel getDrivingLicense() {
        return DRIVINGLICENSE;
    }

    public static ImageModel getOldCard() {
        return OLDCARD;
    }

    public static void clearImages() {
        SELFIE = null;
        IDCARD = null;
        DRIVINGLICENSE = null;
        OLDCARD = null;
    }
}
