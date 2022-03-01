package com.example.barbersapplication.fragments.barberHomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbersapplication.R;
import com.example.barbersapplication.fragments.clientHomePage.ClientHomePageBarbersListAdapter;
import com.example.barbersapplication.model.Barber;
import com.example.barbersapplication.model.BarberShop;
import com.example.barbersapplication.model.Model;

import java.util.List;

public class BarberHomePageBarbersListAdapter extends RecyclerView.Adapter<BarberHomePageBarbersListAdapter.BarberHomePageBarbersViewHolder>{
    private List<Barber> barbers;

    public BarberHomePageBarbersListAdapter(List<Barber> barbers) {
        this.barbers = barbers;
    }


    public class BarberHomePageBarbersViewHolder extends RecyclerView.ViewHolder {

        ImageView workPicIw;
        TextView barberName;

        public BarberHomePageBarbersViewHolder(@NonNull View itemView) {
            super(itemView);

            workPicIw = itemView.findViewById(R.id.barbershop_cell_for_barber_home_page_logo_iw);
            barberName = itemView.findViewById(R.id.barbershop_cell_for_barber_home_page_barbershop_name_tv);


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
    public BarberHomePageBarbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.barbers_cell_for_barber_home_page,parent,false);
        BarberHomePageBarbersViewHolder barberHomePageBarbersViewHolder = new BarberHomePageBarbersViewHolder(view);
        return barberHomePageBarbersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BarberHomePageBarbersViewHolder holder, int position) {
        Barber barber = barbers.get(position);
        holder.barberName.setText(barber.getBarberName());
        holder.workPicIw.setImageResource(R.drawable.barber_image);
    }

    @Override
    public int getItemCount() {
        return barbers.size();
    }
}
