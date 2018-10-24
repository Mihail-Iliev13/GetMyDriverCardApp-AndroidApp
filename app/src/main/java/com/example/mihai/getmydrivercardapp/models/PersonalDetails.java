package com.example.mihai.getmydrivercardapp.models;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

class PersonalDetails implements Serializable{

    private int id;

    private String driverID;

    private String firstNameLatin;

    private String surNameLatin;

    private Date driverBirthDate;

    private String address;

    private String phoneNumber;

    private String email;

    private Blob selfie;

    private byte[] idCardImage;

    private byte[] drivingLicenseImage;

    private byte[] signature;

    private byte[] previousCardImage;

    private String countryIssuedCard;

    private String authorityIssuedCard;

    private String cardNumber;

    private Date dateOfExpiry;

    private Date dateOfLoss;

    private String placeOfLoss;
}
