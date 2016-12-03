package com.henallux.projet.smartpark.modele;

/**
 * Created by Lucas on 03/12/2016.
 */

public class User {

    private int id;
    private String pseudo;
    private String email;
    private String password;
    private String phonenumber;

    public  User()
    {

    }

    public User(int id, String pseudo, String email, String password, String phonenumber) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
