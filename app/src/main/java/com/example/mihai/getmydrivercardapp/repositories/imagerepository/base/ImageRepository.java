package com.example.mihai.getmydrivercardapp.repositories.imagerepository.base;

import com.example.mihai.getmydrivercardapp.models.ImageModel;

import java.io.IOException;
import java.util.List;

public interface ImageRepository {
    ImageModel saveImage(String email, ImageModel image) throws IOException;
    List<ImageModel> getImagesByApplicationID(int id) throws IOException;
}
