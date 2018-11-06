package com.example.mihai.getmydrivercardapp.utils.statusconverter;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.utils.statusconverter.base.ApplicationStatusConverter;

public class ApplicationStatusConverterImpl implements ApplicationStatusConverter {
    @Override
    public CardApplicationStatus fromString(String string) {
        switch (string.toUpperCase()) {
            case "NEW":
                return CardApplicationStatus.NEW;
            case "APPROVED":
                return CardApplicationStatus.APPROVED;
            case "REJECTED":
                return CardApplicationStatus.REJECTED;
            case "COMPLETED":
                return CardApplicationStatus.COMPLETED;
                default:
                    return null;
        }
    }
}
