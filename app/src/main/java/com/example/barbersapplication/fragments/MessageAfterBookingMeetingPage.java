package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.barbersapplication.R;


public class MessageAfterBookingMeetingPage extends Fragment {

    public MessageAfterBookingMeetingPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_after_booking_meeting_page, container, false);
        TextView tv = view.findViewById(R.id.messageafterbookingmettingpage_thanksmessage_tv);
        tv.setText("The barberShop you chose will call you soon! THANKS");

        Button okBtn = view.findViewById(R.id.messageafterbookingmettingpage_returnhomepage_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_messageAfterBookingMettingPage_to_clientHomePage);
            }
        });

        return view;
    }
}