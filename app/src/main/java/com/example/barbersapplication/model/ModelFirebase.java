package com.example.barbersapplication.model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class ModelFirebase {

    public void signUpUserAsClient(Client client, Model.SignUpUserListener listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String mail = client.getEmail();
        String password = client.getPassword();
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userId = user.getUid();
                            DocumentReference documentReference = db.collection("users").document(userId);
                            Map<String, Object> dbUser = new HashMap<>();
                            dbUser.put("Username", client.getUserName());
                            dbUser.put("Email", client.getUserName());
                            dbUser.put("Phone", client.getUserName());
                            dbUser.put("Password", client.getUserName());

                            documentReference.set(dbUser).addOnCompleteListener(taskNewUser -> {
                                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                listener.onComplete();
                            });
                        } else {
                            Log.d("TAG","Error sign up new client");
                        }
                    }
                });
    }
}

