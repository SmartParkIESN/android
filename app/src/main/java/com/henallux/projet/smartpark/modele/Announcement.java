package com.henallux.projet.smartpark.modele;


import java.util.Date;

/**
 * Created by LucasF on 06-12-16.
 */

public class Announcement {

    int AnnouncementId;
    String Title;
    int Price;
    Date DateFrom;
    Date DateTo;
    Boolean Rented;
    Parking parking;
    int parkingId;

    public Announcement()
    {

    }

    public Announcement(int i, String t, int p, Date df, Date dt, Boolean r, Parking pk, int pId)
    {
        AnnouncementId = i;
        Title = t;
        Price = p;
        DateFrom = df;
        DateTo = dt;
        Rented = r;
        parking = pk;
        parkingId = pId;
    }

    public Announcement(String t, int p, Date df, Date dt, Boolean r, int pId)
    {
        Title = t;
        Price = p;
        DateFrom = df;
        DateTo = dt;
        Rented = r;
        parkingId = pId;
    }

    public int getId() {
        return AnnouncementId;
    }

    public void setId(int id) {
        AnnouncementId = id;
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

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
