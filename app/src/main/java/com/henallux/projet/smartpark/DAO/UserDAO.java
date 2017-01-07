package com.henallux.projet.smartpark.DAO;

/**
 * Created by Lucas on 03/12/2016.
 */

import android.util.Log;

import org.json.*;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.henallux.projet.smartpark.business.UserConnected;
import com.henallux.projet.smartpark.exceptions.LoginException;
import com.henallux.projet.smartpark.exceptions.MailException;
import com.henallux.projet.smartpark.exceptions.PasswordException;
import com.henallux.projet.smartpark.exceptions.PhoneException;
import com.henallux.projet.smartpark.exceptions.PseudoException;
import com.henallux.projet.smartpark.exceptions.SignUpException;
import com.henallux.projet.smartpark.modele.User;


public class UserDAO {

    public UserDAO()
    {

    }

    public void signIn(String pseudo, String password) throws LoginException, PseudoException, PasswordException
    {
        if(pseudo != "")
        {
            if(pseudo.length() < 4 || pseudo.length() > 20)
            {
                throw new PseudoException();
            }
        }
        else
        {
            throw new PseudoException();
        }

        if(password != "")
        {
            if(password.length() < 4 || password.length() > 20)
            {
                throw new PasswordException();
            }
        }
        else
        {
            throw new PseudoException();
        }

        try
        {
            User user = getUser(pseudo);
            String passMD5 = MD5(password);
            if(user.getPassword().equals(passMD5))
            {
                UserConnected userConnected = new UserConnected().getINSTANCE();
                userConnected.setConnected(true);
                userConnected.setLastAction();
                userConnected.setUser(user);
            }
            else
            {
                throw new LoginException();
            }
        }
        catch (Exception e)
        {
            throw new LoginException();
        }
    }

    public String MD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    public void signUp(User user) throws Exception, PseudoException, PasswordException, MailException, PhoneException, SignUpException
    {
        User userGet = null;

        if(user.getPseudo().length() < 4 || user.getPseudo().length() > 20)
        {
            throw new PseudoException();
        }

        //Verif si user exist deja via pseudo
        try
        {
            userGet = getUser(user.getPseudo());
        }
        catch (Exception e)
        {

        }

        if(userGet != null)
        {
            throw new SignUpException();
        }
        ////s

        if(user.getEmail().length() < 4 || user.getPseudo().length() > 30)
        {
            throw new MailException("Mail must be between 4 an 30 char.");
        }

        if (!isValidEmailAddress(user.getEmail()))
        {
            throw new MailException("Invalid mail !");
        }
        ////

        if(user.getPassword().length() < 4 || user.getPseudo().length() > 20)
        {
            throw new PasswordException();
        }

        if(!user.getPhonenumber().equals(""))
        {
            if(user.getPhonenumber().length() < 6 || user.getPhonenumber().length() > 18)
            {
                throw new PhoneException("Phone number must be between 6 an 18 char.");
            }

            try
            {
                int phoneInt = Integer.parseInt(user.getPhonenumber());
            }
            catch (NumberFormatException nfe)
            {
                throw new PhoneException("Phone number must be numeric !");
            }
        }



        int responseCode= 0;
        user.setPassword(MD5(user.getPassword()));
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

    }

    public User getUser(String pseudo) throws Exception
    {

        String URL = "http://smartpark1.azurewebsites.net/api/Users/pseudo/" + pseudo;
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

    public User getUserById(int id) throws Exception
    {

        String URL = "http://smartpark1.azurewebsites.net/api/Users/" + id;
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
        user.setUserId(jsonUser.getInt("UserId"));
        user.setPseudo(jsonUser.getString("Pseudo"));
        user.setPassword(jsonUser.getString("Password"));
        user.setEmail(jsonUser.getString("Email"));
        user.setPhonenumber(jsonUser.getString("PhoneNumber"));

        return user;
    }

    public void modifyUser(User user) throws Exception
    {

        if(user.getEmail().length() < 4 || user.getPseudo().length() > 30)
        {
            throw new MailException("Mail must be between 4 an 30 char.");
        }

        if (!isValidEmailAddress(user.getEmail()))
        {
            throw new MailException("Invalid mail !");
        }
        ////

        if(user.getPassword().length() < 4 || user.getPseudo().length() > 20)
        {
            throw new PasswordException();
        }

        if(!user.getPhonenumber().equals(""))
        {
            if(user.getPhonenumber().length() < 6 || user.getPhonenumber().length() > 18)
            {
                throw new PhoneException("Phone number must be between 6 an 18 char.");
            }

            try
            {
                int phoneInt = Integer.parseInt(user.getPhonenumber());
            }
            catch (NumberFormatException nfe)
            {
                throw new PhoneException("Phone number must be numeric !");
            }
        }

        user.setPassword(MD5(user.getPassword()));
        int responseCode= 0;
        URL url = new URL("http://smartpark1.azurewebsites.net/api/Users/" + user.getUserId());
        Log.d("response code", "" + url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
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

    }



}
