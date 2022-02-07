package com.example.barbersapplication.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barbersapplication.R;


public class SignUpAsBarberPage extends Fragment {
    ImageView logo;
    ActivityResultLauncher<String> pictureFullSizeResultLauncher;

    public SignUpAsBarberPage() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_as_barber_page, container, false);
        initLaunchers();
        Button signUpAsBarberBtn = view.findViewById(R.id.signupasbarber_signup_btn);
        Button addLogoBtn = view.findViewById(R.id.signupasbarber_addlogo_btn);
        logo = view.findViewById(R.id.signupasbarber_logo_iv);


        signUpAsBarberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signUpAsBarberPage_to_logInPage);
            }
        });

        addLogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureFullSizeResultLauncher.launch("image/*");
            }
        });


        return view;
    }

    private void initLaunchers(){
        pictureFullSizeResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Uri selectedImage = result;
                logo.setImageURI(selectedImage);
            }
        });
    }
}