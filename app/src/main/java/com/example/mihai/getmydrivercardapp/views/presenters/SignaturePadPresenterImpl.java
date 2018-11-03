package com.example.mihai.getmydrivercardapp.views.presenters;

import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.services.imageservice.base.ImageService;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.utils.BitmapConverter;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.SignaturePadView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.SignaturePadPresenter;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import javax.inject.Inject;

public class SignaturePadPresenterImpl implements SignaturePadPresenter {

    private UserService mUserService;
    private ImageService mImageService;
    private AsyncRunner mAsyncRunner;
    private SignaturePadView mSignaturePadView;
    private BitmapConverter mBitmapConverter;

    @Inject
    public SignaturePadPresenterImpl(UserService userService, ImageService imageService,  AsyncRunner asyncRunner,
                                     BitmapConverter bitmapConverter){
        this.mUserService = userService;
        this.mAsyncRunner = asyncRunner;
        this.mBitmapConverter = bitmapConverter;
        this.mImageService = imageService;
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
    public void setValueToSignature(CardApplication mCardApplication, byte[] byteImage) {
        mCardApplication
                .getDetails()
                .setSignature(byteImage);
    }

    @Override
    public void saveUser(String email, CardApplication cardApplication) {
        mAsyncRunner.runInBackground(() ->{
            try {
                mUserService.updateUserCardApplication(email,cardApplication);
            } catch (IOException e) {
                mSignaturePadView.showError(e);
            }
        });
    }

    @Override
    public void saveImages(String email, List<ImageModel> images) {

        for (ImageModel image : images) {
            mAsyncRunner.runInBackground(() -> {
                try {
                    mImageService.saveImage(email, image);
                } catch (IOException e){
                    mSignaturePadView.showError(e);
                }
            });
        }
    }

    @Override
    public void assignApplicationSignatureValue(Bitmap bitmapImage) {
           byte[] byteArrayImage = mBitmapConverter.toByteArray(bitmapImage);
           mSignaturePadView.setSignature(byteArrayImage);
    }
}
