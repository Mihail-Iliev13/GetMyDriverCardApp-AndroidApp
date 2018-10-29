package com.example.mihai.getmydrivercardapp.views.presenters;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.services.Base.Service;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.SignaturePadView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SignaturePadPresenter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

import javax.inject.Inject;

public class SignaturePadPresenterImpl implements SignaturePadPresenter {

    private Service mService;
    private AsyncRunner mAsyncRunner;
    private SignaturePadView mSignaturePadView;

    @Inject
    public SignaturePadPresenterImpl(Service service, AsyncRunner asyncRunner){
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof SignaturePadView) {
            mSignaturePadView = (SignaturePadView) view;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();    }

    @Override
    public void setValueToSignature(CardApplication mCardApplication, byte[] byteImage) {
        mCardApplication
                .getDetails()
                .setSignature(byteImage);
    }

    @Override
    public void saveUser(String email, CardApplication cardApplication) {
        mAsyncRunner.runInBackground(() ->{
            try {
                mService.updateUserCardApplication(email,cardApplication);
            } catch (IOException e) {
                mSignaturePadView.showError(e);
            }
        });
    }
}
