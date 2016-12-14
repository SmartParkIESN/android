package com.henallux.projet.smartpark.DAO;

import android.util.Log;

import com.henallux.projet.smartpark.modele.Annoucement;
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

    public ArrayList<Annoucement> getAllAnnoucements() throws Exception
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

    private ArrayList<Annoucement> jsonToAnnoucements(String stringJson) throws Exception
    {

        ArrayList<Annoucement> annoucements = new ArrayList<>();
        Annoucement announcement;
        Parking parking;
        User user;
        Place place;

        Date dateFrom;
        Date dateTo;

        JSONArray jsonArray = new JSONArray(stringJson);

        for(int i=0; i<jsonArray.length();i++)
        {
            JSONObject jsonAnnoucement = jsonArray.getJSONObject(i);
            user = new User(jsonAnnoucement.getJSONObject("User").getInt("Id"), jsonAnnoucement.getJSONObject("User").getString("Pseudo"), jsonAnnoucement.getJSONObject("User").getString("Email"), jsonAnnoucement.getJSONObject("User").getString("Password"), jsonAnnoucement.getJSONObject("User").getString("PhoneNumber"));
            place = new Place(jsonAnnoucement.getJSONObject("Parking").getJSONObject("Place").getInt("Id"), jsonAnnoucement.getJSONObject("Parking").getJSONObject("Place").getString("Name"));
            parking = new Parking(jsonAnnoucement.getJSONObject("Parking").getInt("Id"), jsonAnnoucement.getJSONObject("Parking").getString("Name"), jsonAnnoucement.getJSONObject("Parking").getString("Street"), jsonAnnoucement.getJSONObject("Parking").getString("Number"), jsonAnnoucement.getJSONObject("Parking").getString("Picture"), jsonAnnoucement.getJSONObject("Parking").getString("Description"), jsonAnnoucement.getJSONObject("Parking").getInt("Longitude"), jsonAnnoucement.getJSONObject("Parking").getInt("Latitude"), place.getId(), user.getId(), place, user);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateFrom = formatter.parse(jsonAnnoucement.getString("DateFrom"));
            dateTo = formatter.parse(jsonAnnoucement.getString("DateTo"));
            announcement = new Annoucement(jsonAnnoucement.getInt("Id"), jsonAnnoucement.getString("Title"), jsonAnnoucement.getInt("Price"), dateFrom, dateTo, jsonAnnoucement.getBoolean("Rented"), parking);

            annoucements.add(announcement);
        }

        return annoucements;
    }
}
