package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.AnnoucementDAO;
import com.henallux.projet.smartpark.exceptions.PriceException;
import com.henallux.projet.smartpark.exceptions.TitleException;
import com.henallux.projet.smartpark.modele.Announcement;

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

    public ArrayList<Announcement> getAllAnnoucements() throws Exception
    {
        return annoucementDAO.getAllAnnoucements();
    }

    public ArrayList<Announcement> getPriceAnnoucements(String f, String t) throws Exception
    {
        return annoucementDAO.getPriceAnnoucements(f, t);
    }


    public Announcement getAnnoucementById(String id) throws Exception
    {
        return annoucementDAO.getAnnoucementById(id);
    }
    public ArrayList<Announcement> getAnnoucementByUserId(int UserId) throws Exception
    {
        return annoucementDAO.getAnnoucementByUserId(UserId);
    }

    public void postAnnouncement(Announcement announcement) throws Exception, TitleException, PriceException
    {
        annoucementDAO.postAnnouncement(announcement);
    }


}
