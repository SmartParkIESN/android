package com.henallux.projet.smartpark.DAO;

/**
 * Created by Lucas on 03/12/2016.
 */

import android.util.Log;

import org.json.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.henallux.projet.smartpark.modele.User;


public class UserDAO {

    public UserDAO()
    {

    }

    public Boolean signIn(String pseudo, String password) throws Exception
    {
        User user = getUser(pseudo);
        Log.d("Password enter", password);
        Log.d("Password user", user.getPassword());
        if(user != null)
        {
            if(password.equals(user.getPassword()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public Boolean signUp(User user) throws Exception
    {
        int responseCode= 0;
        URL url = new URL("http://smartpark1.azurewebsites.net/api/Users");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        connection.connect();
        writer.write(userToJson(user));
        writer.flush();
        responseCode = connection.getResponseCode();
        writer.close();
        outputStream.close();
        connection.disconnect();

        return true;
    }

    public User getUser(String pseudo) throws Exception
    {

        String URL = "http://smartpark1.azurewebsites.net/api/Users?pseudo=" + pseudo;
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

        return jsonToUser(stringJson);

    }

    public <T> String userToJson(T user)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        return jsonString;
    }

    private User jsonToUser(String stringJson) throws Exception
    {

        User user = new User();

        JSONObject jsonUser = new JSONObject(stringJson);
        user.setId(jsonUser.getInt("Id"));
        user.setPseudo(jsonUser.getString("Pseudo"));
        user.setPassword(jsonUser.getString("Password"));
        user.setEmail(jsonUser.getString("Email"));
        user.setPhonenumber(jsonUser.getString("PhoneNumber"));

        return user;

    }



}
