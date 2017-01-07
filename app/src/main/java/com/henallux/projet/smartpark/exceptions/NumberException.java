package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class NumberException extends Exception {
    public NumberException(){
        super("Number must be between 1 & 6 char. !");
    }
}
