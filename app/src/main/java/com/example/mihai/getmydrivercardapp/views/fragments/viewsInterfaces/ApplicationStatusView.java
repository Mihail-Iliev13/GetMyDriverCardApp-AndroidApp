package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface ApplicationStatusView extends BaseView {
    void setMessageToTextView(String message);
    void setCardApplication(CardApplication cardApplication);

}
