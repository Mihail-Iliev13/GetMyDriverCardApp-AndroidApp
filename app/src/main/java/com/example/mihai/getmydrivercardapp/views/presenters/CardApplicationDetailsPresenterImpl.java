package com.example.mihai.getmydrivercardapp.views.presenters;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;
import com.example.mihai.getmydrivercardapp.constants.Formats;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationReason;
import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.services.imageservice.base.ImageService;
import com.example.mihai.getmydrivercardapp.utils.bitmapconverter.base.BitmapConverter;
import com.example.mihai.getmydrivercardapp.utils.emailsender.EmailSender;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.BaseView;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.CardApplicationDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.CardApplicationDetailsPresenter;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class CardApplicationDetailsPresenterImpl implements CardApplicationDetailsPresenter {

    private CardApplicationDetailsView mCardApplicationDetailsView;
    private BitmapConverter mBitmapConverter;
    private ImageService mImageService;
    private AsyncRunner mAsyncRunner;
    private CardApplicationService mCardApplicationService;

    @Inject
    public CardApplicationDetailsPresenterImpl(CardApplicationService cardApplicationService, ImageService imageService, AsyncRunner asyncRunner,
                                               BitmapConverter bitmapConverter) {
        this.mCardApplicationService = cardApplicationService;
        this.mBitmapConverter = bitmapConverter;
        this.mImageService = imageService;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void subscribe(BaseView view) {
        if (view instanceof  CardApplicationDetailsView) {
            this.mCardApplicationDetailsView = (CardApplicationDetailsView) view;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void assignValues(CardApplication cardApplication) {
        String driverID = cardApplication.getDetails().getDriverID();
        mCardApplicationDetailsView.assignValueToIDTextView(driverID);

        String firstName = cardApplication.getDetails().getFirstNameLatin();
        mCardApplicationDetailsView.assignValueToFirstNameTextView(firstName);

        String surName = cardApplication.getDetails().getSurNameLatin();
        mCardApplicationDetailsView.assignValueToSurnameTextView(surName);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Formats.STRING_DATE_FORMAT);
        String date = simpleDateFormat.format(cardApplication.getDetails().getDriverBirthDate());
        mCardApplicationDetailsView.assignValueToBirthDateTextView(date);

        CardApplicationStatus status = cardApplication.getStatus();
        mCardApplicationDetailsView.assignValueToStatusTextView(status.toString());

        CardApplicationReason reason = cardApplication.getCardApplicationReason();
        mCardApplicationDetailsView.assignValueToReasonTextView(reason.toString());

        String address = cardApplication.getDetails().getAddress();
        mCardApplicationDetailsView.assignValueToAddressTextView(address);

        String phoneNumber = cardApplication.getDetails().getPhoneNumber();
        mCardApplicationDetailsView.assignValueToPhoneNumberTextView(phoneNumber);

        String email = cardApplication.getDetails().getEmail();
        mCardApplicationDetailsView.assignValueToEmailTextView(email);

        byte[] signatureImage = cardApplication.getDetails().getSignature();
        Bitmap bitmapSignature = mBitmapConverter.toBitMap(signatureImage);
        mCardApplicationDetailsView.assignValueToSignatureImageView(bitmapSignature);

        String countryIssuedCard = cardApplication.getDetails().getCountryIssuedCard();
        if (countryIssuedCard != null)
            mCardApplicationDetailsView.assignValueToCountryIssuedCardTextView(countryIssuedCard);

        String authorityIssuedCard = cardApplication.getDetails().getAuthorityIssuedCard();
        if (countryIssuedCard != null)
            mCardApplicationDetailsView.assignValueToAuthorityIssuedCardTextView(authorityIssuedCard);

        String oldCardNumber = cardApplication.getDetails().getCardNumber();
        if (oldCardNumber != null)
            mCardApplicationDetailsView.assignValueToOldCardNumberTextView(oldCardNumber);


        Date dateOfExpiry = cardApplication.getDetails().getDateOfExpiry();
        if (dateOfExpiry != null) {
            String dateOfExpiryStr = simpleDateFormat.format(dateOfExpiry);
            mCardApplicationDetailsView.assignValueToDateOfExpiryTextView(dateOfExpiryStr);

        }

        Date dateOfLoss = cardApplication.getDetails().getDateOfLoss();
        if (dateOfLoss != null) {
            String dateOfLossStr = simpleDateFormat.format(dateOfLoss);
            mCardApplicationDetailsView.assignValueToDateOfLossTextView(dateOfLossStr);
        }

        String placeLost = cardApplication.getDetails().getPlaceOfLoss();
        if (placeLost != null) {
            mCardApplicationDetailsView.assignValueToPlaceLostTextView(placeLost);
        }
    }

    @Override
    public void loadImages(int id) {
        mAsyncRunner.runInBackground(() -> {
            try {
             List<ImageModel> images = mImageService.getImagesByApplicationID(id);
             assIgnImages(images);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void assIgnImages(List<ImageModel> images) {
        for (ImageModel image : images) {
            Bitmap bitmapImage = mBitmapConverter.toBitMap(image.getImage());
            switch (image.getImageAttribute()){
                case DRIVING_LICENSE_IMAGE:
                    mCardApplicationDetailsView
                            .assignValueToDrivingLicenseImageView(bitmapImage);
                    break;
                case ID_CARD_IMAGE:
                    mCardApplicationDetailsView
                            .assignValueToIDCardImageView(bitmapImage);
                    break;
                case OLD_CARD_IMAGE:
                    mCardApplicationDetailsView
                            .assignValueToOldCardImageView(bitmapImage);
                    break;
                case SELFIE_IMAGE:
                    mCardApplicationDetailsView
                            .assignValueToSelfieImageView(bitmapImage);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void updateApplicationStatus(CardApplication mCardApplication, String status) {
        mAsyncRunner.runInBackground( () -> {
           try {
               mCardApplicationService.updateCardApplicationStatus(mCardApplication, status);
           } catch (IOException e) {
               e.printStackTrace();
           }
       });
    }

    @Override
    public void sendEmail(Context context, String email, CardApplicationStatus status) {
        String message = null;
        switch (status) {
            case APPROVED:
                message = "Your application has been approved!";
                break;
            case COMPLETED:
                message = "Your new driver card is ready!";
                break;
            default:
                return;
        }
        EmailSender emailSender = new EmailSender(context, email, "Changed Status", message);
        emailSender.execute();
    }

}
