package com.example.barbersapplication.fragments.SeeBarberPostAsClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barbersapplication.R;
import com.example.barbersapplication.fragments.BarbersListAtBarber.BarbersListAtBarberArgs;
import com.example.barbersapplication.fragments.clientHomePage.ClientHomePageBarbersListAdapter;
import com.example.barbersapplication.model.Barber;
import com.example.barbersapplication.model.Comments;
import com.example.barbersapplication.model.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SeeBarberPostAsClientPage extends Fragment {

    SeeBarberPostAsClientPageCommentsListAdapter seeBarberPostAsClientPageCommentsListAdapter;
    List<Comments>comments;

    public SeeBarberPostAsClientPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_see_barber_post_as_client_page, container, false);
        comments = new ArrayList<>();
        TextView postDescription = view.findViewById(R.id.seebarberpostasclient_barberpostdescription_tv2);
        ImageView postIv = view.findViewById(R.id.seebarberpostasclient_post_iv);
        RecyclerView commentsList = view.findViewById(R.id.seebarberpostasclient_commentslist_rv);
//        EditText addCommentEt = view.findViewById(R.id.seebarberpostasclient_addcomment_et);
//        Button addCommentBtn = view.findViewById(R.id.seebarberpostasclient_addcomment_btn);

        String userName = SeeBarberPostAsClientPageArgs.fromBundle(getArguments()).getUserName();
        String email = SeeBarberPostAsClientPageArgs.fromBundle(getArguments()).getEMail();

        Barber barber = new Barber("non", Uri.parse("non"),"non");
        Model.instance.getBarber(userName, email, new Model.getBarberListener() {
            @Override
            public void onComplete(Barber b) {
                barber.setBarberName(b.getBarberName());
                barber.setWorkPic(b.getWorkPic());
                Log.d("TAG","URI" + b.getWorkPic().toString());
                Picasso.get().load(b.getWorkPic().toString()).into(postIv);
                barber.setComments(b.getComments());
                postDescription.setText(b.getDescription());
                for (String comment: barber.getComments()) {
                    comments.add(new Comments(comment));
                }
                seeBarberPostAsClientPageCommentsListAdapter.notifyDataSetChanged();
            }
        });

        commentsList.setHasFixedSize(true);
        commentsList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        seeBarberPostAsClientPageCommentsListAdapter = new SeeBarberPostAsClientPageCommentsListAdapter(comments);
        commentsList.setAdapter(seeBarberPostAsClientPageCommentsListAdapter);

//        addCommentBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!addCommentEt.getText().toString().isEmpty()){
//                    Model.instance.addComment(new Comments(addCommentEt.getText().toString()), userName, email, new Model.addCommentListener() {
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//                }
//            }
//        });

        return view;
    }
}