package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.ImageAttribute;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

public interface ImageCaptureView extends BaseView{
    void setImageBitmap(Bitmap bitmap);
    void setCurrentUser(User user);
    void setCurrentCardApplication(CardApplication cardApplication);
    void setImageAttribute(ImageAttribute imageAttribute);

}
