package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class TitleException extends Exception{
    public TitleException(){
        super("Title must be between 4 & 30 char. !");
    }
}
