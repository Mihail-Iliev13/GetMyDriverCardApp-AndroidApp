package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.utils.BitmapConverter;
import com.example.mihai.getmydrivercardapp.utils.BitmapConverterImpl;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.ApplicationReasonConverterImpl;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.base.ApplicationReasonConverter;

import dagger.Module;
import dagger.Provides;

@Module
public class UtillitiesBindingModule {

    @Provides
    ApplicationReasonConverter applicationReasonConverter(){
        return new ApplicationReasonConverterImpl();
    }

    @Provides
    BitmapConverter bitmapConverter(){
        return new BitmapConverterImpl();
    }

}
