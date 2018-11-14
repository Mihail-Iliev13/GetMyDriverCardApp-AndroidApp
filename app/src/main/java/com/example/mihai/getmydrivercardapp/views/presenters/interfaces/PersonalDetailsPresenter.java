package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.PersonalDetailsView;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.text.ParseException;
import java.util.List;

public interface PersonalDetailsPresenter {

    void subscribe(PersonalDetailsView view);

    void handleOnClickNext(CardApplicationReason cardApplicationReason,
                           CardApplication cardApplication) throws ParseException;
    void handlePickDateButtonOnClick();
    void checkReasonAndRevealElementsIfNeeded(CardApplicationReason reason);
    void validate();
    void setValidator(Validator validator);

    void handleOnValidationFailed(List<ValidationError> errors);
}
