package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.Model;


public class BarberHomePage extends Fragment {

    public BarberHomePage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barber_home_page, container, false);


        Button logOutBtn = view.findViewById(R.id.barberhomepage_logout_btn);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.instance.logOut();
                Navigation.findNavController(view).navigate(R.id.action_barberHomePage_to_logInPage);
            }
        });


        return view;
    }
}