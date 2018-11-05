package com.example.mihai.getmydrivercardapp.utils.datehandler.base;

import java.text.ParseException;
import java.util.TimeZone;

public interface DateHandler {
    java.util.Date getCurrentDate(TimeZone timeZone) throws ParseException;
}
