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
            case "stolen":
                return CardApplicationReason.STOLEN;
            case "lost":
                return CardApplicationReason.LOST;
            case "changed address":
                return CardApplicationReason.ADDRESS_CHANGE;
            case "changed photo":
                return CardApplicationReason.PHOTO_CHANGE;
            case "changed name":
                return CardApplicationReason.NAME_CHANGE;
            case "damaged":
                return CardApplicationReason.DAMAGED;
            case "malfunctioning":
                return CardApplicationReason.MALFUNCTIONING;
            case "My card has expired":
                return CardApplicationReason.EXPIRED;
            case "withdrawn":
                return CardApplicationReason.WITHDRAWN;
                default:
                    throw new InvalidParameterException();
        }
    }
}
