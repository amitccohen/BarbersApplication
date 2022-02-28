package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.Client;
import com.example.barbersapplication.model.Model;



public class SignUpAsClientPage extends Fragment {

    EditText usernameEt, emailEt, phoneEt, passwordEt;
    View view;

    public SignUpAsClientPage() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up_as_client_page, container, false);

        Button signUpBtn = view.findViewById(R.id.signupasclient_signup_btn);

        usernameEt = view.findViewById(R.id.signupasclient_username_et);
        emailEt = view.findViewById(R.id.signupasclient_email_et);
        phoneEt = view.findViewById(R.id.signupasclient_phone_et);
        passwordEt = view.findViewById(R.id.signupasclient_password_et);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpAsClient();

            }
        });
        return view;
    }

    private void signUpAsClient() {
        String username = usernameEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if (username.isEmpty()){
            usernameEt.setError("Please set your username");
            usernameEt.requestFocus();
            return;
        }
        if(email.isEmpty()) {
            emailEt.setError("Please set your email");
            emailEt.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            phoneEt.setError("Please set your phone");
            phoneEt.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            passwordEt.setError("Please set your password");
            passwordEt.requestFocus();
            return;
        }
        if(password.length() < 6) {
            passwordEt.setError("Password must contain at least 6 characters");
            passwordEt.requestFocus();
            return;
        }

        Client client = new Client(username,email,phone,password);
        Model.instance.signUpUserAsClient(client, new Model.SignUpUserListener() {
            @Override
            public void onComplete() {
                Navigation.findNavController(view).navigate(R.id.action_signUpAsClientPage_to_logInPage);
                //Snackbar.make(view,"Sign up succeed",Snackbar.LENGTH_SHORT).show();
            }
        });


    }


}