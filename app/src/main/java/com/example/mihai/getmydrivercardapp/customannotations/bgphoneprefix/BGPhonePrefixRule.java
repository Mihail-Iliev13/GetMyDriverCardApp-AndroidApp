package com.example.mihai.getmydrivercardapp.customannotations.bgphoneprefix;

import com.mobsandgeeks.saripaar.AnnotationRule;

public class BGPhonePrefixRule extends AnnotationRule<BGPhoneNumber, String> {
    protected BGPhonePrefixRule(BGPhoneNumber bgPhoneNumber) {
        super(bgPhoneNumber);
    }

    @Override
    public boolean isValid(String s) {
        return s.startsWith("089") || s.startsWith("088") || s.startsWith("087");
    }
}
