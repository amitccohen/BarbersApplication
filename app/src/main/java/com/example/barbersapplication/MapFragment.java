package com.example.barbersapplication;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.barbersapplication.model.BarberShop;
import com.example.barbersapplication.model.Model;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener{
    View view;
    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);

        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Geocoder geoCoder = new Geocoder(this.getContext(), Locale.getDefault());
        List<BarberShop>barbershops = new ArrayList<>();
        Model.instance.getBarberShopsList(new Model.getBarberShopsListListener() {
            @Override
            public void onComplete(List<BarberShop> barberShops) {
                for (BarberShop b:barberShops) {
                    barbershops.add(b);
                    double lat,lon;
                    if (b.getBarberAddress().contains("ashkelon")){
                        Log.d("TAG",b.getBarberAddress());
                        List<Address> address = null;
                        try {
                            address = geoCoder.getFromLocationName(
                                    b.getBarberAddress(), 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (address.size() > 0) {
                            lat = address.get(0).getLatitude();
                            lon = address.get(0).getLongitude();
                            Log.d("TAG", "ADRESSE " + address.get(0) + ",LAT :" + address.get(0).getLatitude() + ", LONG :" + address.get(0).getLatitude());
                            LatLng loc = new LatLng(lat, lon);
                            MarkerOptions mo = new MarkerOptions()
                                    .position(loc)
                                    .title(b.getBarberName() + " " + b.getBarberAddress());
                            googleMap.addMarker(mo);
                        }
                    }
                }
            }
        });

        try {
            double lat,lon;
            List<Address> addresses = geoCoder.getFromLocationName(
                    "ashkelon, givati", 1);
            if (addresses.size() > 0) {
                lat = addresses.get(0).getLatitude();
                lon = addresses.get(0).getLongitude();
                Log.d("TAG","ADRESSE "+ addresses.get(0) +",LAT :" + addresses.get(0).getLatitude() +", LONG :" + addresses.get(0).getLatitude() );
                LatLng location = new LatLng(lat, lon);
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(location)
                        .title("ME");
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,15F));
                googleMap.addMarker(markerOptions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(getContext(), "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }
    }
}