package com.example.mihai.getmydrivercardapp.utils.reasonconverter;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.base.ApplicationReasonConverter;

import java.security.InvalidParameterException;

public class ApplicationReasonConverterImpl implements ApplicationReasonConverter {

    @Override
    public CardApplicationReason fromString(String string) {
        String reason = string.toLowerCase();
        switch (reason){
            case "new":
                return CardApplicationReason.NEW_CARD;
            case "exchange":
                return CardApplicationReason.EXCHANGE;
            case "my card was stolen":
                return CardApplicationReason.STOLEN;
            case "i lost my card":
                return CardApplicationReason.LOST;
            case "i changed my address":
                return CardApplicationReason.ADDRESS_CHANGE;
            case "i want to change my photo":
                return CardApplicationReason.PHOTO_CHANGE;
            case "i changed my name":
                return CardApplicationReason.NAME_CHANGE;
            case "my card is damaged":
                return CardApplicationReason.DAMAGED;
            case "my card is malfunctioning":
                return CardApplicationReason.MALFUNCTIONING;
            case "my card has expired":
                return CardApplicationReason.EXPIRED;
            case "my card was withdrawn":
                return CardApplicationReason.WITHDRAWN;
                default:
                    throw new InvalidParameterException();
        }
    }
}
