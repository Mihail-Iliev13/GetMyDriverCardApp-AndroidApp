package com.example.mihai.getmydrivercardapp.views.presenters;

import android.graphics.Bitmap;
import android.view.View;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.services.imageservice.base.ImageService;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.utils.ImageHolder;
import com.example.mihai.getmydrivercardapp.utils.bitmapconverter.base.BitmapConverter;
import com.example.mihai.getmydrivercardapp.utils.datehandler.base.DateHandler;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.SignaturePadView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.SignaturePadPresenter;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;


public class SignaturePadPresenterImpl implements SignaturePadPresenter {

    private UserService mUserService;
    private ImageService mImageService;
    private AsyncRunner mAsyncRunner;
    private SignaturePadView mSignaturePadView;
    private BitmapConverter mBitmapConverter;
    private DateHandler mDateHandler;
    private Validator mValidator;

    @Inject
    public SignaturePadPresenterImpl(UserService userService, ImageService imageService,
                                     AsyncRunner asyncRunner,
                                     BitmapConverter bitmapConverter,
                                     DateHandler dateHandler){
        this.mUserService = userService;
        this.mAsyncRunner = asyncRunner;
        this.mBitmapConverter = bitmapConverter;
        this.mImageService = imageService;
        this.mDateHandler = dateHandler;
    }

    @Override
    public void subscribe(SignaturePadView view) {
            mSignaturePadView = view;
    }


    @Override
    public void saveUser(User user, CardApplication cardApplication) {
        String email = user.getEmail();
        mAsyncRunner.runInBackground(() ->{
            try {
                mUserService.updateUserCardApplication(email,cardApplication);
            } catch (IOException e) {
                mSignaturePadView.showError(e);
            }
        });
    }

    @Override
    public void saveImages(User user) {

        String email = user.getEmail();
            mAsyncRunner.runInBackground(() -> {
                try {
                    mImageService.saveImage(email, ImageHolder.getSelfie());
                    mImageService.saveImage(email, ImageHolder.getIdCard());
                    mImageService.saveImage(email, ImageHolder.getDrivingLicense());

                    if (ImageHolder.getOldCard() != null) {
                        mImageService.saveImage(email, ImageHolder.getOldCard());
                    }
                    ImageHolder.clearImages();
                } catch (IOException e){
                    mSignaturePadView.showError(e);
                }
            });
    }


    @Override
    public void assignSignature(Bitmap bitmapImage, CardApplication cardApplication) {
        byte[] byteArrayImage = mBitmapConverter.toByteArray(bitmapImage);
        cardApplication.getDetails().setSignature(byteArrayImage);
    }

    @Override
    public void assignDateOfSubmission(CardApplication cardApplication) {
        Date currentDate = null;
        try {
            currentDate = mDateHandler.getCurrentDate(TimeZone.getTimeZone("Europe/Sofia"));
        } catch (ParseException e) {
            mSignaturePadView.showError(e);
        }
        cardApplication.setDateOfSubmission(currentDate);
    }

    @Override
    public void validate() {
        mValidator.validate();
    }

    @Override
    public void setValidator(Validator validator) {
        this.mValidator = validator;
    }

    @Override
    public void handleOnValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            Rule failedRule = error.getFailedRules().get(0);
            mSignaturePadView.showValidationError(view, failedRule);

        }
    }
}
