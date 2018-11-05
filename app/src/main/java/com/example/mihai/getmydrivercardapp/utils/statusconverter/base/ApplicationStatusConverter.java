package com.example.mihai.getmydrivercardapp.utils.statusconverter.base;

import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;

public interface ApplicationStatusConverter {
    CardApplicationStatus fromString(String string);
}
