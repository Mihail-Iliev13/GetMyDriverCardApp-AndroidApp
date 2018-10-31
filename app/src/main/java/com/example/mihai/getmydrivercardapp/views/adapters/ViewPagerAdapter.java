package com.example.mihai.getmydrivercardapp.views.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mihai.getmydrivercardapp.views.fragments.PersonalDetailsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        PersonalDetailsFragment fr1 = new PersonalDetailsFragment();
        i=i+1;
        Bundle bundle=new Bundle();
        bundle.putString("message","Fragment :"+i);
        fr1.setArguments(bundle);
        return fr1;

    }

    @Override
    public int getCount() {
        return 3;
    }
}
