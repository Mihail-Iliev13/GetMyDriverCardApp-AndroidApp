package com.example.mihai.getmydrivercardapp.customannotations;

import com.mobsandgeeks.saripaar.AnnotationRule;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatRule extends AnnotationRule<DateFormat, String> {

    protected DateFormatRule(DateFormat dateFormat) {
        super(dateFormat);
    }

    @Override
    public boolean isValid(String dateString) {
            String format  = mRuleAnnotation.format();
            java.util.Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateString);
        } catch (ParseException ex) {
            return false;
        }

        return true;
    }
}
