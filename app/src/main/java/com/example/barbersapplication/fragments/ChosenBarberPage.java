package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.barbersapplication.R;
import com.example.barbersapplication.fragments.BarbersListAtBarber.BarbersListAtBarberArgs;

public class ChosenBarberPage extends Fragment {

    public ChosenBarberPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chosen_barber_page, container, false);

        Button seeBarberPostsBtn = view.findViewById(R.id.choosenbarberpage_seepostsofbarber_btn);
        Button scheduleMeetingBtn = view.findViewById(R.id.choosenbarberpage_schedulemetting_btn);

        String userName = ChosenBarberPageArgs.fromBundle(getArguments()).getUserName();
        String email = ChosenBarberPageArgs.fromBundle(getArguments()).getEMail();

        seeBarberPostsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChosenBarberPageDirections.ActionChoosenBarberPageToSeeBarberPostAsClientPage action =
                        ChosenBarberPageDirections.actionChoosenBarberPageToSeeBarberPostAsClientPage();
                action.setUserName(userName);
                action.setEMail(email);
                Navigation.findNavController(view).navigate(action);
            }
        });

        scheduleMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_choosenBarberPage_to_messageAfterBookingMettingPage);
            }
        });

        return view;
    }
}