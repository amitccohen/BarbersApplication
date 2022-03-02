package com.example.barbersapplication.fragments.BarbersListAtBarber;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barbersapplication.R;
import com.example.barbersapplication.fragments.barberHomePage.BarberHomePageBarbersListAdapter;
import com.example.barbersapplication.model.Barber;
import com.example.barbersapplication.model.BarberShop;
import com.example.barbersapplication.model.Model;

import java.util.ArrayList;
import java.util.List;


public class BarbersListAtBarber extends Fragment {
    List<Barber>data;
    BarbersListAtBarberAdapter barbersListAtBarberAdapter;

    public BarbersListAtBarber() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barbers_list_at_barber, container, false);
        data = new ArrayList<>();

        RecyclerView barbersList = view.findViewById(R.id.barberslistatbarber_barberslist_rv);

        String email = BarbersListAtBarberArgs.fromBundle(getArguments()).getEMail();
        Model.instance.getBarbersListAtBarberShopAsClient(email, new Model.getBarbersListAtBarberShopAsClientListener() {
            @Override
            public void onComplete(List<Barber> barbers) {
                for (Barber b: barbers) {
                    data.add(b);
                }
                barbersListAtBarberAdapter.notifyDataSetChanged();
            }
        });

        barbersList.setHasFixedSize(true);
        barbersList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        barbersListAtBarberAdapter = new BarbersListAtBarberAdapter(data,email);
        barbersList.setAdapter(barbersListAtBarberAdapter);


        return view;
    }
}