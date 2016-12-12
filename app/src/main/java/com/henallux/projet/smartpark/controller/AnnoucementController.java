package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.AnnoucementDAO;
import com.henallux.projet.smartpark.modele.Annoucement;

import java.util.ArrayList;

/**
 * Created by Lucas on 12/12/2016.
 */

public class AnnoucementController {
    private AnnoucementDAO annoucementDAO;

    public AnnoucementController()
    {
        annoucementDAO = new AnnoucementDAO();
    }

    public ArrayList<Annoucement> getAllAnnoucements() throws Exception
    {
        return annoucementDAO.getAllAnnoucements();
    }

}
