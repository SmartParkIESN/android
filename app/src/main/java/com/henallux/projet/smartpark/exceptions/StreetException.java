package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class StreetException extends Exception{
    public StreetException(){
        super("Adress must be between 5 & 40 char. !");
    }
}
