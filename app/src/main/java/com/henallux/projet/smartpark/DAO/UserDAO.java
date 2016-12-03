package com.henallux.projet.smartpark.DAO;

/**
 * Created by Lucas on 03/12/2016.
 */

import android.util.Log;

import org.json.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import com.henallux.projet.smartpark.modele.User;


public class UserDAO {

    public UserDAO()
    {

    }

    public User signIn(String pseudo) throws Exception
    {
        return getUser(pseudo);
    }

    public User getUser(String pseudo) throws Exception
    {

        URL url = new URL("http://smartpark1.azurewebsites.net/api/Users?pseudo=" + pseudo);
        URLConnection connection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJson = "", line;
        while((line= br.readLine()) != null)
        {
            sb.append(line);
        }
        br.close();
        stringJson = sb.toString();

        return jsonToUser(stringJson);

    }

    private User jsonToUser(String stringJson) throws Exception
    {
        User user = new User();
        JSONArray jsonArray = new JSONArray(stringJson);
        for(int i =0; i < jsonArray.length(); i++)
        {
            JSONObject jsonUser = jsonArray.getJSONObject(i);
            user = new User(jsonUser.getInt("Id"), jsonUser.getString("Pseudo"), jsonUser.getString("Email"), jsonUser.getString("Password"), jsonUser.getString("PhoneNumber"));
        }

        return user;

    }



}
