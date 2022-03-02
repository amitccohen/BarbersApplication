package com.example.barbersapplication.fragments.clientHomePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.Barber;
import com.example.barbersapplication.model.BarberShop;
import com.example.barbersapplication.model.Model;

import java.util.ArrayList;
import java.util.List;


public class ClientHomePage extends Fragment {

    List<BarberShop>data;
    ClientHomePageBarbersListAdapter clientHomePageBarbersListAdapter;

    public ClientHomePage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_home_page, container, false);
        data = new ArrayList<>();

        Button mapBtn = view.findViewById(R.id.clienthomepage_seebarbersatmap_btn);
        Button myMeetingBtn = view.findViewById(R.id.clienthomepage_mymeetings_btn);
        Button searchBtn = view.findViewById(R.id.clienthomepage_search_btn);
        RecyclerView barbershopList = view.findViewById(R.id.clienthomepage_barbershopslist_rv);
        Button logOutBtn = view.findViewById(R.id.clienthomepage_logout_btn);
        ProgressBar progressBar = view.findViewById(R.id.clienthomepage_progressbar_pg);
        EditText locationSearchEt = view.findViewById(R.id.clienthomepage_search_et);

        progressBar.setVisibility(View.GONE);

        myMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_clientHomePage_to_myMeetingsPage);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_clientHomePage_to_mapFragment);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationSearchEt.getText().toString().isEmpty()){
                    locationSearchEt.setError("Please set location to search for");
                    locationSearchEt.requestFocus();
                    return;
                }
                else {
                    Model.instance.getBarberShopsList(new Model.getBarberShopsListListener() {
                        @Override
                        public void onComplete(List<BarberShop> barberShops) {
                            data.clear();
                            if (barberShops!=null){
                                for (BarberShop b: barberShops) {
                                    Log.d("TAGLocation",b.getBarberAddress());
                                    Log.d("TAGLocation",locationSearchEt.getText().toString());
                                    if (b.getBarberAddress().equals(locationSearchEt.getText().toString()))
                                        data.add(b);
                                    Log.d("TAG123",b.getBarberName());
                                }
                            }
                            clientHomePageBarbersListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });


        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.instance.logOut();
                Navigation.findNavController(view).navigate(R.id.action_clientHomePage_to_logInPage);
            }
        });

        barbershopList.setHasFixedSize(true);
        barbershopList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        clientHomePageBarbersListAdapter = new ClientHomePageBarbersListAdapter(data);
        barbershopList.setAdapter(clientHomePageBarbersListAdapter);

        return view;
    }
}