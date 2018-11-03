package com.example.mihai.getmydrivercardapp.services.imageservice.base;

import com.example.mihai.getmydrivercardapp.models.ImageModel;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    void saveImages(String email, List<ImageModel> images) throws IOException;

    void saveImage(String email, ImageModel image) throws IOException;
}
