package com.example.barbersapplication.model;

import android.content.Context;
import android.view.View;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import java.util.List;

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

    public void logInLastUser(View view){
        modelFirebase.logInLastUser(view);
    }

    public void addBarberToBarberShop(Barber barber){
        modelFirebase.addBarberToBarberShop(barber);
    }

    public interface getBarbersListAtCurrBarberShopListener{
        void onComplete(List<Barber>barbers);
    }
    public void getBarbersListAtCurrBarberShop(getBarbersListAtCurrBarberShopListener listener){
        modelFirebase.getBarbersListAtCurrBarberShop(listener);
    }

    public interface getBarberShopsListListener{
        void onComplete(List<BarberShop>barberShops);
    }
    public void getBarberShopsList(getBarberShopsListListener listener){
        modelFirebase.getBarberShopsList(listener);
    }

    public interface getBarbersListAtBarberShopAsClientListener{
        void onComplete(List<Barber>barbers);
    }
    public void getBarbersListAtBarberShopAsClient(String eMail, getBarbersListAtBarberShopAsClientListener listener){
        modelFirebase.getBarbersListAtBarberShopAsClient(eMail ,listener);
    }
}
