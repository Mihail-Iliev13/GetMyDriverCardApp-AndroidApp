package com.example.mihai.getmydrivercardapp.services.cardapplicationservice;

import com.example.mihai.getmydrivercardapp.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.repositories.cardapplicationrepository.base.CardApplicationRepository;
import com.example.mihai.getmydrivercardapp.services.cardapplicationservice.base.CardApplicationService;
import com.example.mihai.getmydrivercardapp.utils.emailsender.EmailSender;
import com.example.mihai.getmydrivercardapp.utils.statusconverter.base.ApplicationStatusConverter;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class CardApplicationServiceImpl implements CardApplicationService {

    private CardApplicationRepository mCardApplicationRepository;
    private ApplicationStatusConverter mApplicationStatusConverter;
    private EmailSender mEmailSender;


    public CardApplicationServiceImpl (CardApplicationRepository cardApplicationRepository,
                                       ApplicationStatusConverter applicationStatusConverter) {
        this.mCardApplicationRepository =  cardApplicationRepository;
        this.mApplicationStatusConverter = applicationStatusConverter;

    }
    @Override
    public List<CardApplication> getAllApplications() throws IOException {
        return mCardApplicationRepository.getAllApplications();
    }

    @Override
    public List<CardApplication> filterApplicationsByName(String pattern) throws IOException {
        return mCardApplicationRepository.filterApplicationsByName(pattern);
    }

    @Override
    public List<CardApplication> filterApplicationsByID(String id) throws IOException {
        return mCardApplicationRepository.filterApplicationsByID(id);
    }

    @Override
    public List<CardApplication> filterApplicationsByDate(String pattern) throws ParseException, IOException {
        return mCardApplicationRepository.filterApplicationsByDate(pattern);
    }

    @Override
    public List<CardApplication> filterApplicationsByStatus(String pattern) throws IOException {
       CardApplicationStatus filterStatus = mApplicationStatusConverter.fromString(pattern);
       return mCardApplicationRepository.filterApplicationsByStatus(filterStatus);
    }

    @Override
    public void updateCardApplicationStatus(CardApplication cardApplication, String statusStr) throws IOException {
        CardApplicationStatus status = mApplicationStatusConverter.fromString(statusStr);
        mCardApplicationRepository
                .updateCardApplication(cardApplication, status);
        String email = cardApplication.getDetails().getEmail();
        switch (status) {
            case APPROVED:
                sendEmail(email,
                        "Application status change",
                        "Your application has been approved!" );
                break;
            case COMPLETED:
                sendEmail(email, "Application status change", "Your new driver card is ready!");
                break;
                default:
                    break;
        }
    }

    private void sendEmail(String email, String subject, String message) {
        EmailSender emailSender = new EmailSender(email, subject, message);
        emailSender.execute();
    }
}
