package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class DescriptionException extends Exception {
    public DescriptionException(){
        super("Description must be between 10 & 80 char. !");
    }
}
