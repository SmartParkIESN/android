package com.henallux.projet.smartpark.modele;

/**
 * Created by LucasF on 06-12-16.
 */

public class Place {

    int PlaceId;
    String Name;

    public Place()
    {

    }

    public Place(int id, String name) {
        PlaceId = id;
        Name = name;
    }

    public int getPlaceId() {
        return PlaceId;
    }

    public void setId(int id) {
        PlaceId = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
