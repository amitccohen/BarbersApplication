package com.example.barbersapplication.model;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.example.barbersapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ModelFirebase {
    private String userType = "no type";
    public String getUserType() {return userType;}

    public void signUpUserAsClient(Client client, Model.SignUpUserListener listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mAuth.createUserWithEmailAndPassword(client.getEmail(), client.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String userId = user.getUid();
                                Map<String, Object> DBUser = new HashMap<>();
                                DBUser.put("usertype", "client");
                                DBUser.put("username", client.getUserName());
                                DBUser.put("email", client.getEmail());
                                DBUser.put("phone", client.getPhone());
                                DBUser.put("password", client.getPassword());
                                db.collection("users").document(userId)
                                        .set(DBUser)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("TAG", "DocumentSnapshot successfully written!");
                                                listener.onComplete();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error writing document", e);
                                            }
                                        });
                            }
                        } else {
                            Log.d("TAG", "createUserWithEmail:failed");
                        }
                    }
                });
    }

    public void logIn(String eMail, String password, View view, Model.LogInListener listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mAuth.signInWithEmailAndPassword(eMail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String userID = user.getUid();
                                DocumentReference docRef = db.collection("users").document(userID);
                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                                                if (document.get("usertype").toString().equals("client")) {
                                                    Log.d("TAG","changed to client type");
                                                    userType = "client";
                                                    Navigation.findNavController(view).navigate(R.id.action_logInPage_to_clientHomePage);
                                                    Log.d("TAG",userType);
                                                }
                                                else if (document.get("usertype").toString().equals("barbershop")) {
                                                    Log.d("TAG","changed to barbershop type");
                                                    userType = "barbershop";
                                                    Navigation.findNavController(view).navigate(R.id.action_logInPage_to_barberHomePage);
                                                    Log.d("TAG",userType);
                                                }
                                            } else {
                                                Log.d("TAG", "No such document");
                                            }
                                        } else {
                                            Log.d("TAG", "get failed with ", task.getException());
                                        }
                                    }
                                });
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Snackbar.make(view,"Sign In Failed",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
        listener.onComplete();
    }

    public void logOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void signUpUserAsBarberShop(BarberShop barberShop, Model.SignUpUserListener listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mAuth.createUserWithEmailAndPassword(barberShop.getBarberEmail(), barberShop.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String userId = user.getUid();
                                Map<String, Object> DBUser = new HashMap<>();
                                DBUser.put("usertype", "barbershop");
                                DBUser.put("userName", barberShop.getUserName());
                                DBUser.put("ownerName", barberShop.getOwnerName());
                                DBUser.put("barberName", barberShop.getBarberName());
                                DBUser.put("barberEmail", barberShop.getBarberEmail());
                                DBUser.put("barberPhone", barberShop.getBarberPhone());
                                DBUser.put("barberAddress", barberShop.getBarberAddress());
                                DBUser.put("haircutPrice", barberShop.getHaircutPrice());
                                DBUser.put("logo", String.valueOf(barberShop.getLogo()));
                                DBUser.put("password", barberShop.getPassword());
                                db.collection("users").document(userId)
                                        .set(DBUser)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("TAG", "DocumentSnapshot successfully written!");
                                                listener.onComplete();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error writing document", e);
                                            }
                                        });
                            }
                        } else {
                            Log.d("TAG", "createUserWithEmail:failed");
                        }
                    }
                });
    }
}