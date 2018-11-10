package com.example.mihai.getmydrivercardapp.customannotations.dateformat;


import com.mobsandgeeks.saripaar.annotation.ValidateUsing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidateUsing(DateFormatRule.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateFormat {
    int messageResId()   default -1;                     // Mandatory attribute
    String message()     default "All date fields are required";   // Mandatory attribute
    int sequence()       default -1;

    String format();                         // Your attributes
}
