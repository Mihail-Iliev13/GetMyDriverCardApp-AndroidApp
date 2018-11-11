package com.example.mihai.getmydrivercardapp.services.imageservice.base;

import com.example.mihai.getmydrivercardapp.models.ImageModel;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    void saveImage(String email, ImageModel image) throws IOException;
    List<ImageModel> getImagesByApplicationID(int id) throws IOException;
}
