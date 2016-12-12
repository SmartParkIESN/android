package com.henallux.projet.smartpark.business;

import com.henallux.projet.smartpark.modele.User;

import java.sql.Timestamp;

/**
 * Created by LucasF on 10-12-16.
 */

public class UserConnected {

    private User user;
    private boolean connected;
    private long lastAction;
    private long LIMITCONNECTION = 5000;

    private static UserConnected INSTANCE = null;

    public static UserConnected getINSTANCE()
    {
        if (INSTANCE == null)
        { 	INSTANCE = new UserConnected();
        }
        return INSTANCE;
    }

    public UserConnected()
    {
        connected = false;
    }

    public User getUserConnected()
    {
        return user;
    }

    public long getLastAction()
    {
        return lastAction;
    }

    public boolean getConnected()
    {
        return connected;
    }

    public void setConnected(Boolean co)
    {
        connected = co;
    }

    public void setUser(User u)
    {
        user = u;
    }

    public void setLastAction()
    {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        lastAction = now.getTime();
    }

    public boolean testConnection()
    {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(getLastAction() + LIMITCONNECTION > now.getTime())
        {
            return true;
        }
        else
        {
            setConnected(false);
            return false;
        }
    }

}
