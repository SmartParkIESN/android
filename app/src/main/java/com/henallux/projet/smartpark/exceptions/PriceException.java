package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class PriceException extends Exception{
    public PriceException(){
        super("Price must be > 0");
    }
}
