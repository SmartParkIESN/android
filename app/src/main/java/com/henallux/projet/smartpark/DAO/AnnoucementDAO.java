package com.henallux.projet.smartpark.DAO;

import com.henallux.projet.smartpark.modele.Announcement;
import com.henallux.projet.smartpark.modele.Parking;
import com.henallux.projet.smartpark.modele.Place;
import com.henallux.projet.smartpark.modele.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lucas on 12/12/2016.
 */

public class AnnoucementDAO {

    public AnnoucementDAO()
    {

    }

    public ArrayList<Announcement> getAllAnnoucements() throws Exception
    {
        String URL = "http://smartpark1.azurewebsites.net/api/Announcements";
        java.net.URL url = new URL(URL);
        URLConnection connection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJson = "", line;
        while((line = br.readLine()) != null)
        {
            sb.append(line);

        }
        br.close();
        stringJson = sb.toString();
        return jsonToAnnoucements(stringJson);
    }

    public Announcement getPlaceById(String id) throws Exception
    {

        String URL = "http://smartpark1.azurewebsites.net/api/Announcements/" + id;
        URL url = new URL(URL);
        URLConnection connection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJson = "", line;
        while((line = br.readLine()) != null)
        {
            sb.append(line);

        }
        br.close();
        stringJson = sb.toString();

        return jsonToAnnoucement(stringJson);

    }

    private ArrayList<Announcement> jsonToAnnoucements(String stringJson) throws Exception
    {

        ArrayList<Announcement> annoucements = new ArrayList<>();
        Announcement announcement;
        Parking parking;
        User user;
        Place place;

        Date dateFrom;
        Date dateTo;

        ParkingDAO parkingDAO = new ParkingDAO();
        PlaceDAO placeDAO = new PlaceDAO();
        UserDAO userDAO = new UserDAO();

        JSONArray jsonArray = new JSONArray(stringJson);

        for(int i=0; i<jsonArray.length();i++)
        {
            JSONObject jsonAnnouncement = jsonArray.getJSONObject(i);

            parking = parkingDAO.getParkingById(jsonAnnouncement.getInt("ParkingId"));
            user = userDAO.getUserById(parking.getUserId());
            place = placeDAO.getPlaceById(parking.getPlaceId());

            parking.setPlace(place);
            parking.setUser(user);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateFrom = formatter.parse(jsonAnnouncement.getString("DateFrom"));
            dateTo = formatter.parse(jsonAnnouncement.getString("DateTo"));
            announcement = new Announcement(jsonAnnouncement.getInt("AnnouncementId"), jsonAnnouncement.getString("Title"), jsonAnnouncement.getInt("Price"), dateFrom, dateTo, jsonAnnouncement.getBoolean("Rented"), parking);
            annoucements.add(announcement);
        }

        return annoucements;
    }

    private Announcement jsonToAnnoucement(String stringJson) throws Exception
    {
        Parking parking;
        User user;
        Place place;

        Date dateFrom;
        Date dateTo;

        ParkingDAO parkingDAO = new ParkingDAO();
        PlaceDAO placeDAO = new PlaceDAO();
        UserDAO userDAO = new UserDAO();

        JSONObject jsonAnnouncement = new JSONObject(stringJson);

        parking = parkingDAO.getParkingById(jsonAnnouncement.getInt("ParkingId"));
        user = userDAO.getUserById(parking.getUserId());
        place = placeDAO.getPlaceById(parking.getPlaceId());

        parking.setPlace(place);
        parking.setUser(user);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFrom = formatter.parse(jsonAnnouncement.getString("DateFrom"));
        dateTo = formatter.parse(jsonAnnouncement.getString("DateTo"));
        Announcement announcement = new Announcement(jsonAnnouncement.getInt("AnnouncementId"), jsonAnnouncement.getString("Title"), jsonAnnouncement.getInt("Price"), dateFrom, dateTo, jsonAnnouncement.getBoolean("Rented"), parking);

        return announcement;
    }
}
