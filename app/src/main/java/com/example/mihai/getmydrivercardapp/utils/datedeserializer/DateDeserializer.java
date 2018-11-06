package com.example.mihai.getmydrivercardapp.utils.datedeserializer;

import android.annotation.SuppressLint;

import com.example.mihai.getmydrivercardapp.constants.Formats;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class DateDeserializer implements JsonDeserializer<Date> {
    @SuppressLint("SimpleDateFormat")
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String date = json.getAsString();

        SimpleDateFormat formatter = new SimpleDateFormat(Formats.STRING_DATE_FORMAT);
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Sofia"));
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
