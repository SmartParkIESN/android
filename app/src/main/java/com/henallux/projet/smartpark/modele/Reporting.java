package com.henallux.projet.smartpark.modele;
import java.util.Date;
/**
 * Created by LucasF on 06-12-16.
 */

public class Reporting {

    int Id;
    Date date;
    Announcement Annouce;
    User user;

    public Reporting(int id, Date date, Announcement annouce, User user) {
        Id = id;
        this.date = date;
        Annouce = annouce;
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Announcement getAnnouce() {
        return Annouce;
    }

    public void setAnnouce(Announcement annouce) {
        Annouce = annouce;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
