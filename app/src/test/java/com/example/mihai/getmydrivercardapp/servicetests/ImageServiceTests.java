package com.example.mihai.getmydrivercardapp.servicetests;


import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.repositories.imagerepository.base.ImageRepository;
import com.example.mihai.getmydrivercardapp.services.imageservice.ImageServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTests {

    @Mock
    private ImageRepository mockRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    public void should_returnListOfImages_when_cardApplicationIDIsPassed () throws IOException {
        List<ImageModel> images = new ArrayList<>();
        images.add(new ImageModel());
        images.add(new ImageModel());
        images.add(new ImageModel());

        Mockito.when(mockRepository.getImagesByApplicationID(1))
                .thenReturn(images);

        List<ImageModel> actualImages = imageService.getImagesByApplicationID(1);
        Assert.assertEquals(actualImages.size(), images.size());
    }

}
