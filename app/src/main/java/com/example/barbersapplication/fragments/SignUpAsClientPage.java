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


public class SignUpAsClientPage extends Fragment {

    public SignUpAsClientPage() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_as_client_page, container, false);

        Button signUpBtn = view.findViewById(R.id.signupasclient_signup_btn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signUpAsClientPage_to_logInPage);
            }
        });





        return view;
    }
}