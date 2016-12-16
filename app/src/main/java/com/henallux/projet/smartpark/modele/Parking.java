package com.henallux.projet.smartpark.modele;

/**
 * Created by LucasF on 06-12-16.
 */

public class Parking {

    int ParkingId;
    String Name;
    String Street;
    String Number;
    String Picture;
    String Description;
    double Longitude;
    double Latitude;
    int PlaceId;
    int UserId;
    Place place;
    User user;

    public Parking()
    {

    }

    public Parking(int id, String name, String street, String number, String picture, String description, double longitude, double latitude, int idPlace, int idUser, Place place, User user) {
        ParkingId = id;
        Name = name;
        Street = street;
        Number = number;
        Picture = picture;
        Description = description;
        Longitude = longitude;
        Latitude = latitude;
        PlaceId = idPlace;
        UserId = idUser;
        this.place = place;
        this.user = user;
    }
    public Parking(String name, String street, String number, String picture, String description, int idPlace, int idUser, Place place, User user) {
        Name = name;
        Street = street;
        Number = number;
        Picture = picture;
        Description = description;
        PlaceId = idPlace;
        UserId = idUser;
        this.place = place;
        this.user = user;
    }


    public int getId() {
        return ParkingId;
    }

    public void setId(int id) {
        ParkingId = id;
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

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
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

    public int getPlaceId() {
        return PlaceId;
    }

    public void setPlaceId(int placeId) {
        this.PlaceId = placeId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
    }

}
