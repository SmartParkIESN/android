package com.henallux.projet.smartpark.modele;

import java.util.Date;

/**
 * Created by LucasF on 06-12-16.
 */

public class Annoucement {

    int Id;
    String Title;
    int Price;
    Date DateFrom;
    Date DateTo;
    Boolean Rented;
    User user;
    Parking parking;

    public Annoucement(int i, String t, int p, Date df, Date dt, Boolean r, User u, Parking pk)
    {
        Id = i;
        Title = t;
        Price = p;
        DateFrom = df;
        DateTo = dt;
        Rented = r;
        user = u;
        parking = pk;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }

    public Boolean getRented() {
        return Rented;
    }

    public void setRented(Boolean rented) {
        Rented = rented;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}