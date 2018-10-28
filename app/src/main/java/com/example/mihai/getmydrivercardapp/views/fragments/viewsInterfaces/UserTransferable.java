package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

public interface UserTransferable {
    void setCurrentUser(User user);
    void setCurrentCardApplication(CardApplication cardApplication);
}
