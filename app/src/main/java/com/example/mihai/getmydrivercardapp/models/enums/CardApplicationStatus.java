package com.example.mihai.getmydrivercardapp.models.enums;

import java.util.ArrayList;
import java.util.List;

public enum CardApplicationStatus {
    NEW, APPROVED, REJECTED, COMPLETED;

    public static String[] stringValues() {
        List<String> stringValues = new ArrayList<>();
        for (CardApplicationStatus status : CardApplicationStatus.values()) {
            stringValues.add(status.toString());
        }
        return stringValues.toArray(new String[CardApplicationStatus.values().length]);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
