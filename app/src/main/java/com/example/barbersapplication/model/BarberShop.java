package com.example.barbersapplication.model;

import android.widget.ImageView;

public class BarberShop {
    String userName;
    String ownerName;
    String barberName;
    String barberEmail;
    String barberPhone;
    String barberAddress;
    String password;
    String haircutPrice;
    ImageView logo;

    public BarberShop(String userName, String ownerName, String barberName, String barberEmail, String barberPhone, String barberAddress, String password, String haircutPrice, ImageView logo) {
        this.userName = userName;
        this.ownerName = ownerName;
        this.barberName = barberName;
        this.barberEmail = barberEmail;
        this.barberPhone = barberPhone;
        this.barberAddress = barberAddress;
        this.password = password;
        this.haircutPrice = haircutPrice;
        this.logo = logo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getBarberName() {
        return barberName;
    }

    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }

    public String getBarberEmail() {
        return barberEmail;
    }

    public void setBarberEmail(String barberEmail) {
        this.barberEmail = barberEmail;
    }

    public String getBarberPhone() {
        return barberPhone;
    }

    public void setBarberPhone(String barberPhone) {
        this.barberPhone = barberPhone;
    }

    public String getBarberAddress() {
        return barberAddress;
    }

    public void setBarberAddress(String barberAddress) {
        this.barberAddress = barberAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHaircutPrice() {
        return haircutPrice;
    }

    public void setHaircutPrice(String haircutPrice) {
        this.haircutPrice = haircutPrice;
    }

    public ImageView getLogo() {
        return logo;
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }
}
