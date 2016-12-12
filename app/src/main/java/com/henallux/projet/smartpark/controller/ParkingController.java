package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.ParkingDAO;
import com.henallux.projet.smartpark.modele.Parking;

/**
 * Created by Lucas on 12/12/2016.
 */

public class ParkingController {
    private ParkingDAO parkingDAO;

    public ParkingController()
    {
        parkingDAO = new ParkingDAO();
    }

    public void postParking(Parking parking) throws Exception
    {
        parkingDAO.postParking(parking);
    }

}
