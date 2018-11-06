package com.example.mihai.getmydrivercardapp.views.presenters;

import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.OptionsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.OptionsPresenter;

import java.security.InvalidParameterException;

import javax.inject.Inject;

public class OptionsPresenterImpl implements OptionsPresenter {
    private OptionsView mOptionsView;

    @Inject
    public OptionsPresenterImpl(){
        //
    }

    @Override
    public void subscribe(BaseView view) {
        if(view instanceof OptionsView) {
            this.mOptionsView = (OptionsView) view;
        }
        else throw new InvalidParameterException();
    }
}
