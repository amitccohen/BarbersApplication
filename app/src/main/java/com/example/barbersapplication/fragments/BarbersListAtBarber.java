package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.Barber;
import com.example.barbersapplication.model.Model;

import java.util.List;


public class BarbersListAtBarber extends Fragment {


    public BarbersListAtBarber() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barbers_list_at_barber, container, false);
        String email = BarbersListAtBarberArgs.fromBundle(getArguments()).getEMail();
        Model.instance.getBarbersListAtBarberShopAsClient(email, new Model.getBarbersListAtBarberShopAsClientListener() {
            @Override
            public void onComplete(List<Barber> barbers) {

            }
        });


        return view;
    }
}