package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.ParkingDAO;
import com.henallux.projet.smartpark.exceptions.StreetException;
import com.henallux.projet.smartpark.exceptions.DescriptionException;
import com.henallux.projet.smartpark.exceptions.NumberException;
import com.henallux.projet.smartpark.exceptions.TitleException;
import com.henallux.projet.smartpark.modele.Parking;

import java.util.ArrayList;

/**
 * Created by Lucas on 12/12/2016.
 */

public class ParkingController {
    private ParkingDAO parkingDAO;

    public ParkingController()
    {
        parkingDAO = new ParkingDAO();
    }

    public void postParking(Parking parking) throws Exception, TitleException, DescriptionException, StreetException, NumberException
    {
        parkingDAO.postParking(parking);
    }

    public ArrayList<Parking> getMyParkings() throws Exception
    {
        return parkingDAO.getMyParkings();
    }
}
