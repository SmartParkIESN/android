package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.PlaceDAO;
import com.henallux.projet.smartpark.modele.Place;

import java.util.ArrayList;

/**
 * Created by LucasF on 16-12-16.
 */

public class PlaceController {

    private PlaceDAO placeDAO;

    public PlaceController()
    {
        placeDAO = new PlaceDAO();
    }

    public ArrayList<Place> getPlaces() throws Exception
    {
        return placeDAO.getPlaces();
    }

}
