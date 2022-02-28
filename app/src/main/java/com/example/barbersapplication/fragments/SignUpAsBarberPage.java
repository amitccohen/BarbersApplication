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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.BarberShop;
import com.example.barbersapplication.model.Client;
import com.example.barbersapplication.model.Model;


public class SignUpAsBarberPage extends Fragment {
    ImageView logo;
    EditText usernameEt,ownerNameEt,barberNameEt,barberEmailEt,barberPhoneEt,barberAddressEt,passwordEt,haircutPriceEt;
    ActivityResultLauncher<String> pictureFullSizeResultLauncher;
    View view;
    Uri selectedImage;

    public SignUpAsBarberPage() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up_as_barber_page, container, false);
        initLaunchers();
        Button signUpAsBarberBtn = view.findViewById(R.id.signupasbarber_signup_btn);
        Button addLogoBtn = view.findViewById(R.id.signupasbarber_addlogo_btn);


        logo = view.findViewById(R.id.signupasbarber_logo_iv);
        usernameEt = view.findViewById(R.id.signupasbarber_username_et);
        ownerNameEt = view.findViewById(R.id.signupasbarber_ownername_et);
        barberNameEt = view.findViewById(R.id.signupasbarber_barbername_et);
        barberEmailEt = view.findViewById(R.id.signupasbarber_barberemail_et);
        barberPhoneEt = view.findViewById(R.id.signupasbarber_barberphone_et);
        barberAddressEt = view.findViewById(R.id.signupasbarber_barberaddress_et);
        passwordEt = view.findViewById(R.id.signupasbarber_password_et);
        haircutPriceEt = view.findViewById(R.id.signupasbarber_haircutprice_et);



        signUpAsBarberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpAsBarberShop();
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
                selectedImage = result;
                Log.d("TAGuri", String.valueOf(selectedImage));
                logo.setImageURI(selectedImage);
            }
        });
    }

    private void signUpAsBarberShop() {
        String username = usernameEt.getText().toString().trim();
        String ownerName = ownerNameEt.getText().toString().trim();
        String barberName = barberNameEt.getText().toString().trim();
        String barberEmail = barberEmailEt.getText().toString().trim();
        String barberPhone = barberPhoneEt.getText().toString().trim();
        String barberAddress = barberAddressEt.getText().toString().trim();
        String haircutPrice = haircutPriceEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if (username.isEmpty()){
            usernameEt.setError("Please set your username");
            usernameEt.requestFocus();
            return;
        }
        if(ownerName.isEmpty()) {
            ownerNameEt.setError("Please set your owner name");
            ownerNameEt.requestFocus();
            return;
        }
        if(barberName.isEmpty()) {
            barberNameEt.setError("Please set your barber name");
            barberNameEt.requestFocus();
            return;
        }
        if(barberEmail.isEmpty()) {
            barberEmailEt.setError("Please set your email");
            barberEmailEt.requestFocus();
            return;
        }
        if (barberPhone.isEmpty()){
            barberPhoneEt.setError("Please set your phone");
            barberPhoneEt.requestFocus();
            return;
        }
        if (barberAddress.isEmpty()){
            barberAddressEt.setError("Please set your address");
            barberAddressEt.requestFocus();
            return;
        }
        if (haircutPrice.isEmpty()){
            haircutPriceEt.setError("Please set your haircut price");
            haircutPriceEt.requestFocus();
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

        BarberShop barberShop = new BarberShop(username,ownerName,barberName,barberEmail,barberPhone,
                barberAddress,password,haircutPrice,selectedImage);
        Model.instance.signUpUserAsBarberShop(barberShop, new Model.SignUpUserListener() {
            @Override
            public void onComplete() {
                Navigation.findNavController(view).navigate(R.id.action_signUpAsBarberPage_to_logInPage);
                //Snackbar.make(view,"Sign up succeed",Snackbar.LENGTH_SHORT).show();
            }
        });


    }
}