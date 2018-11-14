package com.example.mihai.getmydrivercardapp.servicetests;


import com.example.mihai.getmydrivercardapp.enums.UserRole;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepository;
import com.example.mihai.getmydrivercardapp.services.userservice.UserServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserRepository mockRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void should_returnUser_when_emailIsPassed() throws IOException {
        String email = "email";
        User expected = new User();
        expected.setEmail(email);
        expected.setUserRole(UserRole.CLIENT);
        expected.setPassword("1234567");

        Mockito.when(mockRepository.getUserByEmail(email)).thenReturn(expected);

        User actual = userService.getUserByEmail(email);
        Assert.assertEquals(actual.getEmail(), expected.getEmail());
        Assert.assertEquals(actual.getPassword(), expected.getPassword());
        Assert.assertEquals(actual.getPassword(), expected.getPassword());
    }


    @Test
    public void should_returnUser_when_userIsUpdated() throws IOException {
        String email = "email";
        User expected = new User();
        CardApplication cardApplication = new CardApplication();
        expected.addCardApplication(cardApplication);
        expected.setEmail(email);
        expected.setUserRole(UserRole.CLIENT);
        expected.setPassword("1234567");

        Mockito.when(mockRepository.updateUserCardApplication(email, cardApplication))
                .thenReturn(expected);

        User actual = userService.updateUserCardApplication(email, cardApplication);
        Assert.assertEquals(actual.getCardApplications().contains(cardApplication),
                expected.getCardApplications().contains(cardApplication));

    }

    @Test
    public void should_returnPendingCardApplication_when_userIsPassed () throws IOException {
        User user = new User();
        CardApplication expected = new CardApplication();

        Mockito.when(mockRepository.getPendingApplication(user))
                .thenReturn(expected);

        CardApplication actual = userService.getPendingApplication(user);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void should_returnAddedUser_when_userIsPassed() throws IOException {
        User user = new User();
        Mockito.when(mockRepository.addUser(user))
                .thenReturn(user);

        User actual =  userService.addNewUser("misho", "12345", UserRole.CLIENT);

        Assert.assertEquals(actual, user);
        Assert.assertEquals(actual.getPassword(), "12345");
        Assert.assertEquals(actual.getUserRole(), UserRole.ADMIN);
    }
}
