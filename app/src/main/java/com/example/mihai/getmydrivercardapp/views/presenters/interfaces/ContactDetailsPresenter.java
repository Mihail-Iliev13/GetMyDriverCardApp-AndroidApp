package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.ContactDetailsView;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

public interface ContactDetailsPresenter {
    void subscribe(ContactDetailsView view);

    void handleOnButtonNextClick(CardApplication cardApplication, String address, String phoneNumber,
                                 String email);
    void validate();

    void setValidator(Validator validator);

    void handleOnValidationFailed(List<ValidationError> errors);
}
