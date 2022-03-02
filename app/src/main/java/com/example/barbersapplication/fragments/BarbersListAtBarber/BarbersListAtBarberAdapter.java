package com.example.barbersapplication.fragments.BarbersListAtBarber;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbersapplication.R;
import com.example.barbersapplication.model.Barber;

import java.util.List;

public class BarbersListAtBarberAdapter extends RecyclerView.Adapter<BarbersListAtBarberAdapter.BarbersListAtBarberViewHolder>{
    private List<Barber>barbers;
    private String email;

    public BarbersListAtBarberAdapter(List<Barber> barbers,String email) {
        this.barbers = barbers;
        this.email = email;
    }

    public class BarbersListAtBarberViewHolder extends RecyclerView.ViewHolder{
        ImageView workPicIw;
        TextView barberName;

        public BarbersListAtBarberViewHolder(@NonNull View itemView) {
            super(itemView);

            workPicIw = itemView.findViewById(R.id.barbershop_cell_for_barber_home_page_logo_iw);
            barberName = itemView.findViewById(R.id.barbershop_cell_for_barber_home_page_barbershop_name_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BarbersListAtBarberDirections.ActionBarbersListAtBarberToChoosenBarberPage action =
                            BarbersListAtBarberDirections.actionBarbersListAtBarberToChoosenBarberPage();
                    action.setUserName(barbers.get(getAdapterPosition()).getBarberName());
                    action.setEMail(email);
                    Navigation.findNavController(v).navigate(action);
                }
            });
        }
    }
    @NonNull
    @Override
    public BarbersListAtBarberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.barbers_cell_for_barber_home_page,parent,false);
        BarbersListAtBarberViewHolder barbersListAtBarberViewHolder = new BarbersListAtBarberViewHolder(view);
        return barbersListAtBarberViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BarbersListAtBarberViewHolder holder, int position) {
        Barber barber = barbers.get(position);
        holder.barberName.setText(barber.getBarberName());
        holder.workPicIw.setImageResource(R.drawable.barber_image);
    }

    @Override
    public int getItemCount() {
        return barbers.size();
    }
}
