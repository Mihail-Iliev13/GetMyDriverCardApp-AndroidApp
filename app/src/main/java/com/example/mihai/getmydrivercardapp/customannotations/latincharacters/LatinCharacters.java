package com.example.mihai.getmydrivercardapp.customannotations.latincharacters;


import com.mobsandgeeks.saripaar.annotation.ValidateUsing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidateUsing(LatinCharacterRule.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LatinCharacters {
    int messageResId()   default -1;                     // Mandatory attribute
    String message()     default "Only latin letters are allowed";   // Mandatory attribute
    int sequence()       default -1;
}
