package com.example.mihai.getmydrivercardapp.models;

import java.io.Serializable;
import java.util.Date;

public class PersonalDetails implements Serializable{

    private int id;
    private String driverID;
    private String firstNameLatin;
    private String surNameLatin;
    private Date driverBirthDate;
    private String address;
    private String phoneNumber;
    private String email;
    private byte[] selfie;
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

    public PersonalDetails () {

    }

    public void setSelfie(byte[] selfie) {
        this.selfie = selfie;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void setFirstNameLatin(String firstNameLatin) {
        this.firstNameLatin = firstNameLatin;
    }

    public void setSurNameLatin(String surNameLatin) {
        this.surNameLatin = surNameLatin;
    }

    public void setDriverBirthDate(Date driverBirthDate) {
        this.driverBirthDate = driverBirthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdCardImage(byte[] idCardImage) {
        this.idCardImage = idCardImage;
    }

    public void setDrivingLicenseImage(byte[] drivingLicenseImage) {
        this.drivingLicenseImage = drivingLicenseImage;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public void setPreviousCardImage(byte[] previousCardImage) {
        this.previousCardImage = previousCardImage;
    }

    public void setCountryIssuedCard(String countryIssuedCard) {
        this.countryIssuedCard = countryIssuedCard;
    }

    public void setAuthorityIssuedCard(String authorityIssuedCard) {
        this.authorityIssuedCard = authorityIssuedCard;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public void setDateOfLoss(Date dateOfLoss) {
        this.dateOfLoss = dateOfLoss;
    }

    public void setPlaceOfLoss(String placeOfLoss) {
        this.placeOfLoss = placeOfLoss;
    }
}
