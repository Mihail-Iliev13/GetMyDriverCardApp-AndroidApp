package com.example.mihai.getmydrivercardapp.services.imageservice;

import com.example.mihai.getmydrivercardapp.models.ImageModel;
import com.example.mihai.getmydrivercardapp.repositories.imagerepository.base.ImageRepository;
import com.example.mihai.getmydrivercardapp.services.imageservice.base.ImageService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class ImageServiceImpl implements ImageService {

    private ImageRepository mImageRepository;

    @Inject
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.mImageRepository = imageRepository;
    }


    @Override
    public void saveImage(String email, ImageModel image) throws IOException {
        mImageRepository.saveImage(email, image);
    }

    @Override
    public List<ImageModel> getImagesByApplicationID(int id) throws IOException {
        return mImageRepository.getImagesByApplicationID(id);
    }
}
