package com.henallux.projet.smartpark.modele;

/**
 * Created by LucasF on 06-12-16.
 */

public class Parking {

    int Id;
    String Name;
    String Street;
    String Number;
    String Picture;
    String Description;
    int Longitude;
    int Latitude;
    Place place;
    User user;

    public Parking(int id, String name, String street, String number, String picture, String description, int longitude, int latitude, Place place, User user) {
        Id = id;
        Name = name;
        Street = street;
        Number = number;
        Picture = picture;
        Description = description;
        Longitude = longitude;
        Latitude = latitude;
        this.place = place;
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getLongitude() {
        return Longitude;
    }

    public void setLongitude(int longitude) {
        Longitude = longitude;
    }

    public int getLatitude() {
        return Latitude;
    }

    public void setLatitude(int latitude) {
        Latitude = latitude;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
