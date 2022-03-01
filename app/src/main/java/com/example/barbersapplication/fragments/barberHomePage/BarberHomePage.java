package com.example.barbersapplication.fragments.barberHomePage;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.barbersapplication.R;
import com.example.barbersapplication.fragments.clientHomePage.ClientHomePageBarbersListAdapter;
import com.example.barbersapplication.model.Barber;
import com.example.barbersapplication.model.BarberShop;
import com.example.barbersapplication.model.Model;

import java.util.ArrayList;
import java.util.List;


public class BarberHomePage extends Fragment {

    List<Barber>data;
    BarberHomePageBarbersListAdapter barberHomePageBarbersListAdapter;

    public BarberHomePage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barber_home_page, container, false);
        data = new ArrayList<>();
        Button logOutBtn = view.findViewById(R.id.barberhomepage_logout_btn);
        Button addBarberBtn = view.findViewById(R.id.barberhomepage_addbarber_btn);
        RecyclerView barbersList = view.findViewById(R.id.barberhomepage_barbers_list_rv);

        Model.instance.getBarbersListAtCurrBarberShop(new Model.getBarbersListAtCurrBarberShopListener() {
            @Override
            public void onComplete(List<Barber> barbers) {
                if (barbers!=null){
                    for (Barber b: barbers) {
                        data.add(b);
                        Log.d("TAG123",b.getBarberName());
                    }
                }
                barberHomePageBarbersListAdapter.notifyDataSetChanged();
            }
        });
        Log.d("TAGreload","created");
        barbersList.setHasFixedSize(true);
        barbersList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        barberHomePageBarbersListAdapter = new BarberHomePageBarbersListAdapter(data);
        barbersList.setAdapter(barberHomePageBarbersListAdapter);


        addBarberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_barberHomePage_to_addBarber);
            }
        });


        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.instance.logOut();
                Navigation.findNavController(view).navigate(R.id.action_barberHomePage_to_logInPage);
            }
        });


        return view;
    }
}