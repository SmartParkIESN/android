package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class PasswordException extends Exception {
    public PasswordException(){
        super("Password must be between 4 & 20 char. !");
    }
}
