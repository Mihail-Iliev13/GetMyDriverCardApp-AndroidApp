package com.example.mihai.getmydrivercardapp.utils.reasonconverter.base;

import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationReason;

public interface ApplicationReasonConverter {
    CardApplicationReason fromString(String string);
}
