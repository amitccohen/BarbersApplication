package com.example.barbersapplication.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.Barber;
import com.example.barbersapplication.model.Model;

public class AddBarber extends Fragment {

    ActivityResultLauncher<String> pictureFullSizeResultLauncher;
    ImageView workPicIw;
    Uri selectedImage;

    public AddBarber() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_barber, container, false);
        initLaunchers();


        Button addPicBtn = view.findViewById(R.id.addbarber_addpictures_btn);
        Button saveBtn = view.findViewById(R.id.addbarber_save_btn);
        EditText barberNameEt = view.findViewById(R.id.addbarber_nameinput_et);
        workPicIw = view.findViewById(R.id.addbarber_workpic_iw);


        addPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictureFullSizeResultLauncher.launch("image/*");
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (barberNameEt.getText().toString().isEmpty()){
                    barberNameEt.setError("Please set barber name");
                    barberNameEt.requestFocus();
                    return;
                }
                else if (selectedImage == null){
                    Toast.makeText(getContext(), "You need to add photo of your work!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Model.instance.addBarberToBarberShop(new Barber(barberNameEt.getText().toString(), selectedImage));
                    Navigation.findNavController(view).navigate(R.id.action_addBarber_to_barberHomePage);
                }
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
                workPicIw.setImageURI(selectedImage);
            }
        });
    }
}