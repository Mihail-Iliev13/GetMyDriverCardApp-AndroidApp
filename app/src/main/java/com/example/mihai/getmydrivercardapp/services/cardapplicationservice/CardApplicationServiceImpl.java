package com.example.mihai.getmydrivercardapp.services.cardapplicationservice;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.base.CardApplicationRepository;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;

import java.io.IOException;
import java.util.List;

public class CardApplicationServiceImpl implements CardApplicationService {

    private CardApplicationRepository mCardApplicationRepository;

    public CardApplicationServiceImpl (CardApplicationRepository cardApplicationRepository) {
        this.mCardApplicationRepository =  cardApplicationRepository;

    }
    @Override
    public List<CardApplication> getAllApplications() throws IOException {
        return mCardApplicationRepository.getAllApplications();
    }
}
