package com.example.barbersapplication.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ModelFirebase {
    private String userType = "no type";

    public String getUserType() {return userType;}

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

    public void logInLastUser(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (currentUser != null){
            Log.d("TAGlastlog","noNull");
            String userID = currentUser.getUid();
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
    }

    public void addBarberToBarberShop(Barber barber) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            Map<String, Object> DBUser = new HashMap<>();
            DBUser.put("barberName", barber.getBarberName());
            DBUser.put("workPicUri", String.valueOf(barber.getWorkPic()));
            DBUser.put("description", barber.getDescription());
            DBUser.put("comments", Arrays.asList());
            db.collection(user.getUid()).document(barber.getBarberName())
                    .set(DBUser)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error writing document", e);
                        }
                    });
        }
    }

    public void getBarbersListAtCurrBarberShop(Model.getBarbersListAtCurrBarberShopListener listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.getUid() != null){
            db.collection(user.getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            List<Barber>barbers =new LinkedList<>();
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                    String barberName = documentSnapshot.get("barberName").toString();
                                    String description = documentSnapshot.get("description").toString();
                                    Uri workPicUri = Uri.parse(documentSnapshot.get("workPicUri").toString());
                                    Barber barber = new Barber(barberName,workPicUri,description);
                                    barbers.add(barber);

                                }
                            }
                            listener.onComplete(barbers);
                        }
                    });
        }
    }

    public void getBarberShopsList(Model.getBarberShopsListListener listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        db.collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<BarberShop>barberShops =new LinkedList<>();
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                if (documentSnapshot.get("usertype").equals("barbershop")){
                                    String uN = documentSnapshot.get("userName").toString();
                                    String oN = documentSnapshot.get("ownerName").toString();
                                    String bN = documentSnapshot.get("barberName").toString();
                                    String bE = documentSnapshot.get("barberEmail").toString();
                                    String bP = documentSnapshot.get("barberPhone").toString();
                                    String bA = documentSnapshot.get("barberAddress").toString();
                                    String hP = documentSnapshot.get("haircutPrice").toString();
                                    String lO = documentSnapshot.get("logo").toString();
                                    String pA = documentSnapshot.get("password").toString();
                                    BarberShop barberShop = new BarberShop(uN,oN,bN,bE,bP,bA,pA,hP,Uri.parse(lO));
                                    barberShops.add(barberShop);
                                }
                            }
                        }
                        listener.onComplete(barberShops);
                    }
                });
    }

    public void getBarbersListAtBarberShopAsClient(String eMail ,Model.getBarbersListAtBarberShopAsClientListener listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                if (documentSnapshot.get("barberEmail") != null){
                                    if (documentSnapshot.get("barberEmail").toString().equals(eMail)){
                                        String uId = documentSnapshot.getId();
                                        db.collection(uId).get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        List<Barber>barbers =new LinkedList<>();
                                                        if (task.isSuccessful()){
                                                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                                                String barberName = documentSnapshot.get("barberName").toString();
                                                                String description = documentSnapshot.get("description").toString();
                                                                Uri workPicUri = Uri.parse(documentSnapshot.get("workPicUri").toString());
                                                                Barber barber = new Barber(barberName,workPicUri,description);
                                                                barbers.add(barber);
                                                            }
                                                        }
                                                        listener.onComplete(barbers);
                                                    }
                                                });
                                    }
                                }
                            }
                        }
                    }
                });
    }

    public void getBarber(String userName,String email, Model.getBarberListener listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                if (documentSnapshot.get("barberEmail") != null){
                                    if (documentSnapshot.get("barberEmail").toString().equals(email)){
                                        String uId = documentSnapshot.getId();
                                        db.collection(uId).get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()){
                                                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                                                if (userName.equals(documentSnapshot.get("barberName").toString())){
                                                                    String barberName = documentSnapshot.get("barberName").toString();
                                                                    String description = documentSnapshot.get("description").toString();
                                                                    Uri workPicUri = Uri.parse(documentSnapshot.get("workPicUri").toString());
                                                                    if (!documentSnapshot.get("comments").toString().isEmpty()){
                                                                        List<String>list = Collections.singletonList(documentSnapshot.get("comments").toString());
                                                                        Log.d("TAGSplit",list.get(0));
                                                                        String comm = list.get(0);
                                                                        comm = comm.substring(1, comm.length() - 1);
                                                                        List<String>comments = Arrays.asList(comm.split(","));
                                                                        Barber barber = new Barber(barberName,workPicUri, comments,description);
                                                                        listener.onComplete(barber);
                                                                    }
                                                                    else {
                                                                        Barber barber = new Barber(barberName,workPicUri,description);
                                                                        listener.onComplete(barber);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                    }
                                }
                            }
                        }
                    }
                });
    }

    public void addComment(Comments comment,String userName,String email,Model.addCommentListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                if (documentSnapshot.get("barberEmail").toString().equals(email)){
                                    String uId = documentSnapshot.getId();
                                    Map<String, Object> DBUser = new HashMap<>();
                                    DBUser.put("barberName", userName);
                                    DBUser.put("workPicUri", "TEST");
                                    DBUser.put("comments", Arrays.asList(comment,"sdafsad"));
                                    db.collection(uId).document(userName)
                                            .set(DBUser)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("TAG", "DocumentSnapshot successfully written!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("TAG", "Error writing document", e);
                                                }
                                            });
                                    listener.onComplete();
                                }
                                break;
                            }
                        }
                    }
                });
    }

    public void uploadImage(Bitmap imageBmp, String name, final Model.UploadImageListener listener){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference imagesRef = storage.getReference().child("barbersWork").child(name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBmp.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        UploadTask uploadTask = imagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onComplete(null);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri downloadUri = uri;
                        listener.onComplete(downloadUri.toString());
                    }
                });
            }
        });
    }
}