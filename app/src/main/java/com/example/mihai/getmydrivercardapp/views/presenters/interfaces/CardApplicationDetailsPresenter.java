package com.example.mihai.getmydrivercardapp.views.presenters.interfaces;

import android.content.Context;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;

import java.util.List;

public interface CardApplicationDetailsPresenter extends BasePresenter{
    void assignValues(CardApplication cardApplication);
    void loadImages(int id);
    void assIgnImages(List<ImageModel> images);
    void updateApplicationStatus(CardApplication mCardApplication, String status);

    void sendEmail(Context context, String email, CardApplicationStatus status);
}
