package com.example.mihai.getmydrivercardapp.repositories.imagerepository.base;

import com.example.mihai.getmydrivercardapp.models.ImageModel;

import java.io.IOException;

public interface ImageRepository {
    ImageModel saveImage(String email, ImageModel image) throws IOException;
}
