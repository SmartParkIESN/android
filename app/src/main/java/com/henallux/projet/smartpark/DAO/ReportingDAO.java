package com.henallux.projet.smartpark.DAO;

import com.google.gson.Gson;
import com.henallux.projet.smartpark.modele.Reporting;

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
 * Created by Lucas on 17/12/2016.
 */

public class ReportingDAO {

    public ReportingDAO()
    {

    }

    public void postReporting(Reporting reporting) throws Exception
    {
        int responseCode= 0;
        URL url = new URL("http://smartpark1.azurewebsites.net/api/Reportings");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        connection.connect();
        writer.write(parkingToJson(reporting));
        writer.flush();
        responseCode = connection.getResponseCode();
        writer.close();
        outputStream.close();
        connection.disconnect();

    }

    public <T> String parkingToJson(T reporting)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(reporting);
        return jsonString;
    }
}
