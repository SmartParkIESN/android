package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class AdressException extends Exception  {
    public AdressException(){
        super("This adress does not exist !");
    }
}
