package com.example.barbersapplication.model;

import android.content.Context;
import android.view.View;

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

    public void signUpUserAsBarberShop(BarberShop barberShop, SignUpUserListener listener){
        modelFirebase.signUpUserAsBarberShop(barberShop,listener);
    }

    public interface LogInListener{
        void onComplete();
    }
    public void logIn(String eMail, String password, View view, LogInListener listener){
        modelFirebase.logIn(eMail,password,view, listener);
    }
    public String getUserType(){
        return modelFirebase.getUserType();
    }

    public void logOut(){
        modelFirebase.logOut();
    }
}
