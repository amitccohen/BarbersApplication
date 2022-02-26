package com.example.barbersapplication.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

public class Model {

    public static final Model instance = new Model();
    ModelFirebase modelFirebase = new ModelFirebase();


    private Model(){}

    public interface SignUpUserListener {
        void onComplete();
    }
    public void signUpUserAsClient(Client client, SignUpUserListener listener){
        modelFirebase.signUpUserAsClient(client,listener);
    }

}
