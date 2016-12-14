package com.henallux.projet.smartpark.modele;

/**
 * Created by LucasF on 06-12-16.
 */

public class Place {

    int Id;
    String Name;

    public Place()
    {

    }

    public Place(int id, String name) {
        Id = id;
        Name = name;
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
}
