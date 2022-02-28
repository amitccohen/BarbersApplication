package com.example.barbersapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;

import com.example.barbersapplication.R;

public class EditBarber extends Fragment {

    public EditBarber() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_edit_barber, container, false);
        EditText nameEt=view.findViewById(R.id.editbarber_name_et);
        Button deleteBtn=view.findViewById(R.id.editbarber_delete_btn);
        Button saveBtn=view.findViewById(R.id.editbarber_save_btn);


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}