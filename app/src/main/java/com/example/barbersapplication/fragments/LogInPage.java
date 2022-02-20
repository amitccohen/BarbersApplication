package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.barbersapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogInPage extends Fragment {

    String NO_INPUT_LOGIN_ERROR = "invalid_user_error";

    public LogInPage() {
        // Required empty public constructor
    }

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_log_in_page, container, false);

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
                String username , password;
                if (TextUtils.isEmpty(usernameEt.getText().toString()))
                    username = NO_INPUT_LOGIN_ERROR;
                else username = usernameEt.getText().toString();
                if (TextUtils.isEmpty(passwordEt.getText().toString()))
                    password = NO_INPUT_LOGIN_ERROR;
                else password = passwordEt.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Snackbar.make(view,"Log in succeed",Snackbar.LENGTH_SHORT).show();

                        }
                        else {
                            Snackbar.make(view,"Log in failed",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        return view;
    }
}