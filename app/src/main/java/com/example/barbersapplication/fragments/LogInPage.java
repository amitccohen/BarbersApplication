package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.barbersapplication.R;
import com.example.barbersapplication.model.BarberShop;
import com.example.barbersapplication.model.Client;
import com.example.barbersapplication.model.Model;
import com.google.android.material.snackbar.Snackbar;


public class LogInPage extends Fragment {

    String NO_INPUT_LOGIN_ERROR = "invalid_user_error";

    public LogInPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_log_in_page, container, false);
        Model.instance.logInLastUser(view);
        TextView signUpAsClientTv = view.findViewById(R.id.loginpage_signupasclient_tv);
        TextView signUpAsBarberTv = view.findViewById(R.id.loginpage_signupasbarber_tv);
        Button loginBtn = view.findViewById(R.id.loginpage_login_btn);
        EditText usernameEt = view.findViewById(R.id.loginpage_username_et);
        EditText passwordEt = view.findViewById(R.id.loginpage_password_et);


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

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eMail , password;
                if (TextUtils.isEmpty(usernameEt.getText().toString()))
                    eMail = NO_INPUT_LOGIN_ERROR;
                else eMail = usernameEt.getText().toString();
                if (TextUtils.isEmpty(passwordEt.getText().toString()))
                    password = NO_INPUT_LOGIN_ERROR;
                else password = passwordEt.getText().toString();
                Model.instance.logIn(eMail, password, view ,new Model.LogInListener() {
                    @Override
                    public void onComplete() {

                    }
                });
                String userType = Model.instance.getUserType();
//                if (userType.equals("client")){
//                    Navigation.findNavController(view).navigate(R.id.action_logInPage_to_clientHomePage);
//                }
//                if (userType.equals("barbershop")){
//                    Navigation.findNavController(view).navigate(R.id.action_logInPage_to_barberHomePage);
//                }
            }
        });

        return view;
    }
}