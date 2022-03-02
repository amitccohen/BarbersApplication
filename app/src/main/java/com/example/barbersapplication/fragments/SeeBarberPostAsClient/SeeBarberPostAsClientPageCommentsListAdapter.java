package com.example.barbersapplication.fragments.SeeBarberPostAsClient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbersapplication.R;
import com.example.barbersapplication.fragments.clientHomePage.ClientHomePageBarbersListAdapter;
import com.example.barbersapplication.fragments.clientHomePage.ClientHomePageDirections;
import com.example.barbersapplication.model.BarberShop;
import com.example.barbersapplication.model.Comments;

import java.util.List;

public class SeeBarberPostAsClientPageCommentsListAdapter extends RecyclerView.Adapter<SeeBarberPostAsClientPageCommentsListAdapter.SeeBarberPostAsClientPageViewHolder>{

    private List<Comments>comments;

    public SeeBarberPostAsClientPageCommentsListAdapter(List<Comments> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public SeeBarberPostAsClientPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_cell,parent,false);
        SeeBarberPostAsClientPageViewHolder seeBarberPostAsClientPageViewHolder = new SeeBarberPostAsClientPageViewHolder(view);
        return seeBarberPostAsClientPageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeeBarberPostAsClientPageViewHolder holder, int position) {
        Comments comment = comments.get(position);
        holder.comment.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class SeeBarberPostAsClientPageViewHolder extends RecyclerView.ViewHolder{
        TextView comment;

        public SeeBarberPostAsClientPageViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment_cell_comment_tv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
