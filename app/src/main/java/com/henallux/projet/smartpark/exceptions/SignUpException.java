package com.henallux.projet.smartpark.exceptions;

/**
 * Created by Lucas on 07/01/2017.
 */

public class SignUpException extends Exception{
    public SignUpException(){
        super("User already exist !");
    }
}
