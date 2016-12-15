package com.henallux.projet.smartpark.modele;

/**
 * Created by Lucas on 03/12/2016.
 */

public class User {

    private int UserId;
    private String Pseudo;
    private String Email;
    private String Password;
    private String PhoneNumber;

    public  User()
    {

    }

    public User(int id, String pseudo, String email, String password, String phonenumber) {
        this.UserId = id;
        this.Pseudo = pseudo;
        this.Email = email;
        this.Password = password;
        this.PhoneNumber = phonenumber;
    }

    public User(String pseudo, String email, String password, String phonenumber) {
        this.Pseudo = pseudo;
        this.Email = email;
        this.Password = password;
        this.PhoneNumber = phonenumber;
    }

    public void setUserId(int id) {
        this.UserId = id;
    }

    public void setPseudo(String pseudo) {
        this.Pseudo = pseudo;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.PhoneNumber = phonenumber;
    }

    public int getUserId() {
        return UserId;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhonenumber() {
        return PhoneNumber;
    }
}
