package com.example.mihai.getmydrivercardapp.models;

import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationReason;

import java.io.Serializable;
import java.util.Date;

public class CardApplication implements Serializable {
    private int id;
    private CardApplicationStatus status;
    private PersonalDetails details;
    private CardApplicationReason cardApplicationReason;
    private Date dateOfSubmission;
    private User user;

    public CardApplication () {

    }

    public CardApplicationStatus getStatus() {
        return status;
    }

    public PersonalDetails getDetails() {
        return details;
    }

    public CardApplicationReason getCardApplicationReason() {
        return cardApplicationReason;
    }

    public Date getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDetails(PersonalDetails details) {
        this.details = details;
    }

    public void setStatus(CardApplicationStatus status) {
        this.status = status;
    }

    public void setCardApplicationReason(CardApplicationReason cardApplicationReason) {
        this.cardApplicationReason = cardApplicationReason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateOfSubmission(Date dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
