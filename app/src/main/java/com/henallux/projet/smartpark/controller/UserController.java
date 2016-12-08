package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.UserDAO;
import com.henallux.projet.smartpark.modele.User;

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

    public Boolean signUp(User user) throws Exception
    {
        return userDAO.signUp(user);
    }
}
