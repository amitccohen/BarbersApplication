package com.example.barbersapplication.model;

import android.net.Uri;

public class Barber {
    private String barberName;
    private Uri workPic;

    public Barber(String barberName, Uri workPic) {
        this.barberName = barberName;
        this.workPic = workPic;
    }

    public String getBarberName() {
        return barberName;
    }

    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }

    public Uri getWorkPic() {
        return workPic;
    }

    public void setWorkPic(Uri workPic) {
        this.workPic = workPic;
    }
}
