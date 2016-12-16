package com.henallux.projet.smartpark.DAO;

import android.util.Log;

import com.google.gson.Gson;
import com.henallux.projet.smartpark.modele.Parking;
import com.henallux.projet.smartpark.modele.Place;

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

public class PlaceDAO {

    public PlaceDAO()
    {

    }

    public Place getPlaceById(int id) throws Exception
    {

        String URL = "http://smartpark1.azurewebsites.net/api/Places/" + id;
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

        return jsonToPlace(stringJson);
    }

    public ArrayList<Place> getPlaces() throws Exception
    {
        String URL = "http://smartpark1.azurewebsites.net/api/Places";
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
        return jsonToPlaces(stringJson);
    }

    private Place jsonToPlace(String stringJson) throws Exception
    {

        Place place = new Place();

        JSONObject jsonPlace = new JSONObject(stringJson);
        place.setId(jsonPlace.getInt("PlaceId"));
        place.setName(jsonPlace.getString("Name"));

        return place;

    }

    private ArrayList<Place> jsonToPlaces(String stringJson) throws Exception
    {

        ArrayList<Place> places = new ArrayList<>();
        Place place;

        JSONArray jsonArray = new JSONArray(stringJson);

        for(int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonPlace = jsonArray.getJSONObject(i);
            place = new Place(jsonPlace.getInt("PlaceId"), jsonPlace.getString("Name"));
            places.add(place);

        }

        return places;
    }


}
