package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class PseudoException extends Exception{
    public PseudoException(){
        super("Pseudo must be between 4 & 20 char. !");
    }
}
