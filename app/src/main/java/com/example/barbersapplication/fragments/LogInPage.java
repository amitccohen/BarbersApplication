package com.example.barbersapplication.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.barbersapplication.R;


public class LogInPage extends Fragment {

    public LogInPage() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_log_in_page, container, false);

        TextView signUpAsClientTv = view.findViewById(R.id.loginpage_signupasclient_tv);
        TextView signUpAsBarberTv = view.findViewById(R.id.loginpage_signupasbarber_tv);

        signUpAsClientTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_logInPage_to_signUpAsClientPage);
            }
        });

        signUpAsBarberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_logInPage_to_signUpAsBarberPage);
            }
        });


        return view;
    }
}