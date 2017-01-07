package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.UserDAO;
import com.henallux.projet.smartpark.exceptions.LoginException;
import com.henallux.projet.smartpark.exceptions.MailException;
import com.henallux.projet.smartpark.exceptions.PasswordException;
import com.henallux.projet.smartpark.exceptions.PhoneException;
import com.henallux.projet.smartpark.exceptions.PseudoException;
import com.henallux.projet.smartpark.exceptions.SignUpException;
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

    public void signIn(String pseudo, String password) throws LoginException, PseudoException, PasswordException
    {
        userDAO.signIn(pseudo, password);
    }

    public void signUp(User user) throws Exception, PseudoException, PasswordException, MailException, PhoneException, SignUpException
    {
        userDAO.signUp(user);
    }

    public void modifyUser(User user) throws Exception, PasswordException, MailException, PhoneException
    {
        userDAO.modifyUser(user);
    }
}
