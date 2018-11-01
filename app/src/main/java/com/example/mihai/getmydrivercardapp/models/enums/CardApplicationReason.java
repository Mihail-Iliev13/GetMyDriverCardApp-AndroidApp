package com.example.mihai.getmydrivercardapp.models.enums;

public enum CardApplicationReason {
    NEW_CARD("New"),EXCHANGE("Exchange"),LOST("Lost"),
    STOLEN("Stolen"),MALFUNCTIONING("Malfunctioning"),DAMAGED("Damaged"),
    ADDRESS_CHANGE("Changed Address"),NAME_CHANGE("Changed Name"),PHOTO_CHANGE ("Changed Photo"),
    EXPIRED("Expired"), WITHDRAWN("Withdrawn");

    private String stringValue;

    CardApplicationReason(String string){
        this.stringValue = string;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
