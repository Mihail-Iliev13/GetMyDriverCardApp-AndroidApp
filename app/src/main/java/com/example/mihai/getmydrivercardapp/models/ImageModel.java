package com.example.mihai.getmydrivercardapp.models;

import com.example.mihai.getmydrivercardapp.enums.ImageAttribute;

import java.io.Serializable;

public class ImageModel implements Serializable{

    private int id;
    private ImageAttribute imageAttribute;
    private byte[] image;
    private PersonalDetails personalDetails;

    public ImageModel () {

    }


    public ImageAttribute getImageAttribute() {
        return imageAttribute;
    }

    public void setImageAttribute(ImageAttribute imageAttribute) {
        this.imageAttribute = imageAttribute;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }
}
