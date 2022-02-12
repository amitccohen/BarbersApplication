package com.example.barbersapplication.fragments.clientHomePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.BarberShop;

import java.util.ArrayList;
import java.util.List;


public class ClientHomePage extends Fragment {

    public ClientHomePage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_home_page, container, false);

        Button myMeetingBtn = view.findViewById(R.id.clienthomepage_mymeetings_btn);
        Button searchBtn = view.findViewById(R.id.clienthomepage_search_btn);
        RecyclerView barbershopList = view.findViewById(R.id.clienthomepage_barbershopslist_rv);

        myMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_clientHomePage_to_myMeetingsPage);
            }
        });


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_clientHomePage_to_barbersListAtBarber);
            }
        });



        barbershopList.setHasFixedSize(true);
        barbershopList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        List<BarberShop> barberShops = new ArrayList<>();
        barberShops.add(new BarberShop("PAPU","Zuri","PAPUBARBERS","AAA","1234","Ashkelon","123","50"));
        barberShops.add(new BarberShop("PAPU","Zuri","PAPUBARBERS","AAA","1234","Ashkelon","123","50"));
        barberShops.add(new BarberShop("PAPU","Zuri","PAPUBARBERS","AAA","1234","Ashkelon","123","50"));
        barberShops.add(new BarberShop("PAPU","Zuri","PAPUBARBERS","AAA","1234","Ashkelon","123","50"));
        barberShops.add(new BarberShop("PAPU","Zuri","PAPUBARBERS","AAA","1234","Ashkelon","123","50"));
        barberShops.add(new BarberShop("PAPU","Zuri","PAPUBARBERS","AAA","1234","Ashkelon","123","50"));

        ClientHomePageBarbersListAdapter clientHomePageBarbersListAdapter = new ClientHomePageBarbersListAdapter(barberShops);
        barbershopList.setAdapter(clientHomePageBarbersListAdapter);


        return view;
    }
}