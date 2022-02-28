package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barbersapplication.R;


public class AddPicturesOfBarberWorks extends Fragment {



    public AddPicturesOfBarberWorks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_pictures_of_barber_works, container, false);
    }
}