package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.barbersapplication.R;


public class MessageAfterSignUpAsBarberShop extends Fragment {

    public MessageAfterSignUpAsBarberShop() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_after_sign_up_as_barber_shop, container, false);
        Button okBtn= view.findViewById(R.id.messageAfterSignAsBarber_ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}