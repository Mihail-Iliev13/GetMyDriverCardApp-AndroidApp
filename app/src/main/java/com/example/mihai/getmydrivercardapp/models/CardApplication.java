package com.example.mihai.getmydrivercardapp.models;

import java.io.Serializable;
import java.util.Date;

public class CardApplication implements Serializable {
    private int id;
    private CardAppStatus status;
    private PersonalDetails details;
    private Reason reason;
    private Date dateOfSubmission;

    public CardAppStatus getStatus() {
        return status;
    }
}
