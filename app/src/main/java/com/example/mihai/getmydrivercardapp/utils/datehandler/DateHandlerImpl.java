package com.example.mihai.getmydrivercardapp.utils.datehandler;

import android.annotation.SuppressLint;

import com.example.mihai.getmydrivercardapp.StringConstants;
import com.example.mihai.getmydrivercardapp.utils.datehandler.base.DateHandler;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class DateHandlerImpl implements DateHandler {

    @Override
    @SuppressLint({"DefaultLocale", "SimpleDateFormat"})
    public Date getCurrentDate(TimeZone timeZone) throws ParseException {
        Calendar calendar = Calendar.getInstance(timeZone);
         String dateString = String.format("%d/%d/%d",
                calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),
                 calendar.get(Calendar.YEAR));

        return new SimpleDateFormat(StringConstants.STRING_DATE_FORMAT).parse(dateString);
    }
}
