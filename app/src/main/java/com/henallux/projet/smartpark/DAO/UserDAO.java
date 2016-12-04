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
