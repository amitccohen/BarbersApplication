package com.example.barbersapplication.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class Barber {
    private String barberName;
    private Uri workPic;
    private List <String> comments;
    private String description;

    public Barber(String barberName, Uri workPic, String description) {
        this.barberName = barberName;
        this.workPic = workPic;
        this.description = description;
        this.comments = new ArrayList<>();
    }

    public Barber(String barberName, Uri workPic, List<String> comments,String description) {
        this.barberName = barberName;
        this.workPic = workPic;
        this.comments = comments;
        this.description = description;
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

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
