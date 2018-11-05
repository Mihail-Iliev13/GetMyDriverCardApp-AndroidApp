package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.utils.bitmapconverter.base.BitmapConverter;
import com.example.mihai.getmydrivercardapp.utils.bitmapconverter.BitmapConverterImpl;
import com.example.mihai.getmydrivercardapp.utils.datehandler.DateHandlerImpl;
import com.example.mihai.getmydrivercardapp.utils.datehandler.base.DateHandler;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.ApplicationReasonConverterImpl;
import com.example.mihai.getmydrivercardapp.utils.reasonconverter.base.ApplicationReasonConverter;
import com.example.mihai.getmydrivercardapp.utils.statusconverter.ApplicationStatusConverterImpl;
import com.example.mihai.getmydrivercardapp.utils.statusconverter.base.ApplicationStatusConverter;

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

    @Provides
    ApplicationStatusConverter applicationStatusConverter() {
        return new ApplicationStatusConverterImpl();
    }

    @Provides
    DateHandler dateHandler() {
        return new DateHandlerImpl();
    }

}
