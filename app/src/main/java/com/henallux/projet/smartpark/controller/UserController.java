package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.UserDAO;

/**
 * Created by LucasF on 06-12-16.
 */

public class UserController {
    private UserDAO userDAO;

    public UserController()
    {
        userDAO = new UserDAO();
    }

    public Boolean signIn(String pseudo, String password) throws Exception
    {
        return userDAO.signIn(pseudo, password);
    }
}
