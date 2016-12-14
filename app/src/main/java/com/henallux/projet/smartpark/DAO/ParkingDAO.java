package com.henallux.projet.smartpark.DAO;

import android.util.Log;

import com.google.gson.Gson;
import com.henallux.projet.smartpark.modele.Parking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Lucas on 12/12/2016.
 */

public class ParkingDAO {

    public ParkingDAO()
    {

    }

    public void postParking(Parking parking) throws Exception
    {
        double position[] = geocalisation(parking);

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

    public <T> String parkingToJson(T parking)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(parking);
        return jsonString;
    }

    public double[] geocalisation(Parking parking) throws Exception
    {
        String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=" + parking.getNumber() + "+" + parking.getStreet() + "+Namur+BE&key=AIzaSyAa5KwFPV6nzXsrusVBFi4KNGKokKecc7I" ;
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

    private double[] jsonToGeocalisation(String stringJson) throws Exception
    {

        JSONObject jsonGeo = new JSONObject(stringJson);

        double lng = ((JSONArray)jsonGeo.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lng");

        double lat = ((JSONArray)jsonGeo.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lat");

        double position[] = {lat, lng};
        return position;

    }
}
