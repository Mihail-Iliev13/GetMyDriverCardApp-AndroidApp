package com.example.mihai.getmydrivercardapp.models;

import com.example.mihai.getmydrivercardapp.models.enums.CardAppStatus;
import com.example.mihai.getmydrivercardapp.models.enums.Reason;

import java.io.Serializable;
import java.util.Date;

public class CardApplication implements Serializable {
    private int id;
    private CardAppStatus status;
    private PersonalDetails details;
    private Reason reason;
    private Date dateOfSubmission;

    public CardApplication () {

    }

    public CardAppStatus getStatus() {
        return status;
    }

    public PersonalDetails getDetails() {
        return details;
    }

    public Reason getReason() {
        return reason;
    }

    public Date getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDetails(PersonalDetails details) {
        this.details = details;
    }

    public void setStatus(CardAppStatus status) {
        this.status = status;
    }
}
