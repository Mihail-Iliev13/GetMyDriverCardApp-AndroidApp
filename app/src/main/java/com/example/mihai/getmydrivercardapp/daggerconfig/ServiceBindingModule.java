package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.repositories.imagerepository.base.ImageRepository;
import com.example.mihai.getmydrivercardapp.repositories.userrepository.base.UserRepository;
import com.example.mihai.getmydrivercardapp.services.imageservice.ImageServiceImpl;
import com.example.mihai.getmydrivercardapp.services.imageservice.base.ImageService;
import com.example.mihai.getmydrivercardapp.services.userservice.base.UserService;
import com.example.mihai.getmydrivercardapp.services.userservice.UserServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceBindingModule {

    @Provides
    @Singleton
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Provides
    @Singleton
    public ImageService imageService(ImageRepository imageRepository) {
        return new ImageServiceImpl(imageRepository);
    }
}