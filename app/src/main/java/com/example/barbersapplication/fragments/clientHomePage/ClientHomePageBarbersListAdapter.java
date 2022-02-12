package com.example.barbersapplication.fragments.clientHomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.BarberShop;

import java.util.List;

public class ClientHomePageBarbersListAdapter extends RecyclerView.Adapter <ClientHomePageBarbersListAdapter.ClientHomePageBarbersViewHolder> {

    private List<BarberShop> barbershops;

    public ClientHomePageBarbersListAdapter(List<BarberShop> barbershops) {
        this.barbershops = barbershops;
    }

    public class ClientHomePageBarbersViewHolder extends RecyclerView.ViewHolder {

        ImageView logoIw;
        TextView barbershopName;
        TextView barbershopLocation;

        public ClientHomePageBarbersViewHolder(@NonNull View itemView) {
            super(itemView);

            logoIw = itemView.findViewById(R.id.barbershop_cell_for_client_home_page_logo_iw);
            barbershopName = itemView.findViewById(R.id.barbershop_cell_for_client_home_page_barbershop_name_tv);
            barbershopLocation = itemView.findViewById(R.id.barbershop_cell_for_client_home_page_location_tv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Worked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public ClientHomePageBarbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.barbershop_cell_for_client_home_page,parent,false);
        ClientHomePageBarbersViewHolder clientHomePageBarbersViewHolder = new ClientHomePageBarbersViewHolder(view);
        return clientHomePageBarbersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHomePageBarbersViewHolder holder, int position) {
        BarberShop barberShop = barbershops.get(position);
        holder.barbershopName.setText(barberShop.getBarberName());
        holder.barbershopLocation.setText(barberShop.getBarberAddress());
        holder.logoIw.setImageResource(R.drawable.barbers_app_logo);
    }

    @Override
    public int getItemCount() {
        return barbershops.size();
    }
}
