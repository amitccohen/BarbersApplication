package com.example.barbersapplication.model;


import android.graphics.Bitmap;

import android.view.View;


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

    public interface getBarberListener{
        void onComplete(Barber barber);
    }
    public void getBarber(String userName,String email, getBarberListener listener){
        modelFirebase.getBarber(userName, email,listener);
    }

    public interface addCommentListener{
        void onComplete();
    }
    public void addComment(Comments comment,String username,String email,addCommentListener listener){
        modelFirebase.addComment(comment,username,email,listener);
    }

    public interface UploadImageListener{
        void onComplete(String url);
    }
    public void uploadImage(Bitmap imageBmp, String name,final UploadImageListener listener) {
        modelFirebase.uploadImage(imageBmp, name, listener);
    }
}
