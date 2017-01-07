package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class LoginException extends Exception{
    public LoginException(){
        super("Log in error. Check your input data.");
    }
}
