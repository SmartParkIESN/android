package com.henallux.projet.smartpark.DAO;

import com.google.gson.Gson;
import com.henallux.projet.smartpark.business.UserConnected;
import com.henallux.projet.smartpark.exceptions.AdressException;
import com.henallux.projet.smartpark.exceptions.StreetException;
import com.henallux.projet.smartpark.exceptions.DescriptionException;
import com.henallux.projet.smartpark.exceptions.NumberException;
import com.henallux.projet.smartpark.exceptions.TitleException;
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
import java.util.ArrayList;

/**
 * Created by Lucas on 12/12/2016.
 */

public class ParkingDAO {

    public ParkingDAO()
    {

    }

    public void postParking(Parking parking) throws Exception, TitleException, DescriptionException, StreetException, NumberException, AdressException
    {

        if(parking.getName().length() < 4 || parking.getName().length() > 20)
        {
            throw new TitleException();
        }
        if(parking.getDescription().length() < 10 || parking.getDescription().length() > 80)
        {
            throw new DescriptionException();
        }
        if(parking.getStreet().length() < 5 || parking.getStreet().length() > 40)
        {
            throw new StreetException();
        }
        if(parking.getNumber().length() < 1 || parking.getNumber().length() > 6)
        {
            throw new NumberException();
        }

        double position[] = geocalisation(parking);

        parking.setUser(null);
        parking.setPlace(null);
        parking.setLatitude(position[0]);
        parking.setLongitude(position[1]);

        int responseCode= 0;
        URL url = new URL("http://smartpark1.azurewebsites.net/api/Parkings");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        connection.connect();
        writer.write(parkingToJson(parking));
        writer.flush();
        responseCode = connection.getResponseCode();
        writer.close();
        outputStream.close();
        connection.disconnect();

    }
    public Parking getParkingById(int id) throws Exception
    {

        String URL = "http://smartpark1.azurewebsites.net/api/Parkings/" + id;
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

        return jsonToParking(stringJson);

    }

    public ArrayList<Parking> getMyParkings() throws Exception
    {
        UserConnected userConnected = new UserConnected().getINSTANCE();
        User user = userConnected.getUserConnected();

        String URL = "http://smartpark1.azurewebsites.net/api/Parkings/user/" + user.getUserId();
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

        return jsonToParkings(stringJson);
    }


    private ArrayList<Parking> jsonToParkings(String stringJson) throws Exception
    {

        ArrayList<Parking> parkings = new ArrayList<>();
        Parking parking;
        User user;
        Place place;

        PlaceDAO placeDAO = new PlaceDAO();
        UserDAO userDAO = new UserDAO();

        JSONArray jsonArray = new JSONArray(stringJson);

        for(int i=0; i<jsonArray.length();i++)
        {
            JSONObject jsonParking = jsonArray.getJSONObject(i);


            user = new User(jsonParking.getJSONObject("user").getInt("UserId"), jsonParking.getJSONObject("user").getString("Pseudo"), jsonParking.getJSONObject("user").getString("Email"), jsonParking.getJSONObject("user").getString("Password"), jsonParking.getJSONObject("user").getString("PhoneNumber"));
            place = new Place(jsonParking.getJSONObject("place").getInt("PlaceId"), jsonParking.getJSONObject("place").getString("Name"));
            parking = new Parking(jsonParking.getInt("ParkingId"), jsonParking.getString("Name"), jsonParking.getString("Street"), jsonParking.getString("Number"), jsonParking.getString("Picture"), jsonParking.getString("Description"), jsonParking.getDouble("Longitude"), jsonParking.getDouble("Latitude"), jsonParking.getInt("PlaceId"), jsonParking.getInt("UserId"), place, user);

            parkings.add(parking);
        }

        return parkings;
    }

    private Parking jsonToParking(String stringJson) throws Exception
    {

        Parking parking = new Parking();

        JSONObject jsonParking = new JSONObject(stringJson);
        parking.setId(jsonParking.getInt("ParkingId"));
        parking.setDescription(jsonParking.getString("Description"));
        parking.setLatitude(jsonParking.getDouble("Latitude"));
        parking.setLongitude(jsonParking.getDouble("Longitude"));
        parking.setName(jsonParking.getString("Name"));
        parking.setNumber(jsonParking.getString("Number"));
        parking.setPicture(jsonParking.getString("Picture"));
        parking.setPlaceId(jsonParking.getInt("PlaceId"));
        parking.setStreet(jsonParking.getString("Street"));
        parking.setUserId(jsonParking.getInt("UserId"));

        return parking;

    }


    public <T> String parkingToJson(T parking)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(parking);
        return jsonString;
    }

    public double[] geocalisation(Parking parking) throws Exception, AdressException
    {

        String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=" + parking.getNumber() + "+" + parking.getStreet() + "+" + parking.getPlace().getName() +"+BE&key=AIzaSyAa5KwFPV6nzXsrusVBFi4KNGKokKecc7I" ;
        URL = URL.replaceAll("\\s", "");
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

        return jsonToGeocalisation(stringJson);
    }

    private double[] jsonToGeocalisation(String stringJson) throws Exception, AdressException
    {

        JSONObject jsonGeo = new JSONObject(stringJson);
        double position[] = {0, 0};
        String status = jsonGeo.getString("status");

        if(status.equals("ZERO_RESULTS"))
        {
            throw new AdressException();
        }
        else
        {
            double lng = ((JSONArray)jsonGeo.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");

            double lat = ((JSONArray)jsonGeo.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");

            position[0] = lat;
            position[1] = lng;
        }

        return position;

    }
}
