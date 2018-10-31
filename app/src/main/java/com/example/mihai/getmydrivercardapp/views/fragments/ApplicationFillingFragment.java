package com.example.mihai.getmydrivercardapp.views.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mihai.getmydrivercardapp.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicationFillingFragment extends Fragment {

    @BindView(R.id.et_user_name)
    EditText mName;
    @BindView(R.id.et_userid)
    EditText mUserID;
    @BindView(R.id.et_)
    EditText mUserID;
    @BindView(R.id.et_)
    EditText mUserID;
    @BindView(R.id.et_userid)
    EditText mUserID;
    @BindView(R.id.et_userid)
    EditText mUserID;
    @BindView(R.id.btn_next)
    Button mNext;

    public ApplicationFillingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_application_filling, container, false);
    }

}
