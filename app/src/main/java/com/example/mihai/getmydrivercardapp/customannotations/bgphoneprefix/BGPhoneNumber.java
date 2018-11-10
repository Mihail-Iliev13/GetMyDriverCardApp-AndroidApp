package com.example.mihai.getmydrivercardapp.customannotations.bgphoneprefix;

import com.mobsandgeeks.saripaar.annotation.ValidateUsing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidateUsing(BGPhonePrefixRule.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BGPhoneNumber {
    int messageResId()   default -1;                     // Mandatory attribute
    String message()     default "Invalid number";   // Mandatory attribute
    int sequence();
}
