package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import com.mobsandgeeks.saripaar.Validator;

public interface ContactDetailsPresenter extends BasePresenter{
    void handleOnButtonNextClick();
    void validate();

    void setValidator(Validator validator);
}
