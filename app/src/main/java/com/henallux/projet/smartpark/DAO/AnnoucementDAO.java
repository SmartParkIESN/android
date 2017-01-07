package com.henallux.projet.smartpark.DAO;

import android.util.Log;

import com.google.gson.Gson;
import com.henallux.projet.smartpark.exceptions.PriceException;
import com.henallux.projet.smartpark.exceptions.TitleException;
import com.henallux.projet.smartpark.modele.Announcement;
import com.henallux.projet.smartpark.modele.Parking;
import com.henallux.projet.smartpark.modele.Place;
import com.henallux.projet.smartpark.modele.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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


    public ArrayList<Announcement> getPriceAnnoucements(String f, String t) throws Exception
    {
        String URL = "http://smartpark1.azurewebsites.net/api/Announcements/price/" + f +"/" + t;
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

    public Announcement getAnnoucementById(String id) throws Exception
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

    public ArrayList<Announcement> getAnnoucementByUserId(int UserId) throws Exception
    {

        String URL = "http://smartpark1.azurewebsites.net/api/Announcements/user/" + UserId;
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

        return jsonToAnnoucements(stringJson);
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


            user = new User(jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getInt("UserId"), jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getString("Pseudo"), jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getString("Email"), jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getString("Password"), jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getString("PhoneNumber"));
            place = new Place(jsonAnnouncement.getJSONObject("parking").getJSONObject("place").getInt("PlaceId"), jsonAnnouncement.getJSONObject("parking").getJSONObject("place").getString("Name"));
            parking = new Parking(jsonAnnouncement.getJSONObject("parking").getInt("ParkingId"), jsonAnnouncement.getJSONObject("parking").getString("Name"), jsonAnnouncement.getJSONObject("parking").getString("Street"), jsonAnnouncement.getJSONObject("parking").getString("Number"), jsonAnnouncement.getJSONObject("parking").getString("Picture"), jsonAnnouncement.getJSONObject("parking").getString("Description"), jsonAnnouncement.getJSONObject("parking").getDouble("Longitude"), jsonAnnouncement.getJSONObject("parking").getDouble("Latitude"), jsonAnnouncement.getJSONObject("parking").getInt("PlaceId"), jsonAnnouncement.getJSONObject("parking").getInt("UserId"), place, user);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateFrom = formatter.parse(jsonAnnouncement.getString("DateFrom"));
            dateTo = formatter.parse(jsonAnnouncement.getString("DateTo"));
            announcement = new Announcement(jsonAnnouncement.getInt("AnnouncementId"), jsonAnnouncement.getString("Title"), jsonAnnouncement.getInt("Price"), dateFrom, dateTo, jsonAnnouncement.getBoolean("Rented"), parking, parking.getId());
            annoucements.add(announcement);
        }

        return annoucements;
    }

    public void postAnnouncement(Announcement announcement) throws Exception, TitleException, PriceException
    {

        if(announcement.getTitle().length() < 4 || announcement.getTitle().length() > 30)
        {
            throw new TitleException();
        }

        if(announcement.getPrice() < 1)
        {
            throw new PriceException();
        }


        announcement.setParking(null);

        int responseCode= 0;
        URL url = new URL("http://smartpark1.azurewebsites.net/api/Announcements");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        connection.connect();
        writer.write(AnnouncementToJson(announcement));
        writer.flush();
        responseCode = connection.getResponseCode();
        writer.close();
        outputStream.close();
        connection.disconnect();

    }

    public <T> String AnnouncementToJson(T announcement)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(announcement);
        return jsonString;
    }

    private Announcement jsonToAnnoucement(String stringJson) throws Exception
    {
        Parking parking;
        User user;
        Place place;

        Date dateFrom;
        Date dateTo;

        JSONArray jsonArray = new JSONArray(stringJson);
        JSONObject jsonAnnouncement = jsonArray.getJSONObject(0);



        user = new User(jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getInt("UserId"), jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getString("Pseudo"), jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getString("Email"), jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getString("Password"), jsonAnnouncement.getJSONObject("parking").getJSONObject("user").getString("PhoneNumber"));
        place = new Place(jsonAnnouncement.getJSONObject("parking").getJSONObject("place").getInt("PlaceId"), jsonAnnouncement.getJSONObject("parking").getJSONObject("place").getString("Name"));
        parking = new Parking(jsonAnnouncement.getJSONObject("parking").getInt("ParkingId"), jsonAnnouncement.getJSONObject("parking").getString("Name"), jsonAnnouncement.getJSONObject("parking").getString("Street"), jsonAnnouncement.getJSONObject("parking").getString("Number"), jsonAnnouncement.getJSONObject("parking").getString("Picture"), jsonAnnouncement.getJSONObject("parking").getString("Description"), jsonAnnouncement.getJSONObject("parking").getDouble("Longitude"), jsonAnnouncement.getJSONObject("parking").getDouble("Latitude"), jsonAnnouncement.getJSONObject("parking").getInt("PlaceId"), jsonAnnouncement.getJSONObject("parking").getInt("UserId"), place, user);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFrom = formatter.parse(jsonAnnouncement.getString("DateFrom"));
        dateTo = formatter.parse(jsonAnnouncement.getString("DateTo"));
        Announcement announcement = new Announcement(jsonAnnouncement.getInt("AnnouncementId"), jsonAnnouncement.getString("Title"), jsonAnnouncement.getInt("Price"), dateFrom, dateTo, jsonAnnouncement.getBoolean("Rented"), parking, parking.getId());

        return announcement;
    }
}
