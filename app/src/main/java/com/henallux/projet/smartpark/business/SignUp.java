package com.henallux.projet.smartpark.business;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.UserController;
import com.henallux.projet.smartpark.exceptions.MailException;
import com.henallux.projet.smartpark.exceptions.PasswordException;
import com.henallux.projet.smartpark.exceptions.PhoneException;
import com.henallux.projet.smartpark.exceptions.PseudoException;
import com.henallux.projet.smartpark.exceptions.SignUpException;
import com.henallux.projet.smartpark.modele.User;

/**
 * Created by Lucas on 23/11/2016.
 */

public class SignUp extends AppCompatActivity {

    UserController userController = new UserController();
    private Button SignUpBtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //CLICK SIGN UP
        SignUpBtt = (Button) findViewById(R.id.signUpButton);
        SignUpBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                EditText editPseudo = (EditText) findViewById(R.id.SignUpPseudo);
                EditText editMail = (EditText) findViewById(R.id.SignUpMail);
                EditText editPassword = (EditText) findViewById(R.id.SignUpPass);
                EditText editPasswordConf = (EditText) findViewById(R.id.SignUpPassConf);
                EditText editPhone = (EditText) findViewById(R.id.SignUpTel);
                String pseudo = editPseudo.getText().toString();
                String mail = editMail.getText().toString();
                String password = editPassword.getText().toString();
                String passwordConf = editPasswordConf.getText().toString();
                String phoneNumber = editPhone.getText().toString();

                if(password.equals(passwordConf))
                {
                    user.setPseudo(pseudo);
                    user.setEmail(mail);
                    user.setPassword(password);
                    user.setPhonenumber(phoneNumber);

                    new signUp().execute(user);
                }
                else
                {
                    Toast.makeText(SignUp.this, "Passwords don't match !", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private class signUp extends AsyncTask<User, Void, String>
    {

        @Override
        protected String doInBackground(User... log) {

            UserController userController = new UserController();
            try
            {
                userController.signUp(log[0]);
                return "Sign up successful !";
            }
            catch (SignUpException e)
            {
                return e.getMessage();
            }
            catch (PseudoException e)
            {
                return e.getMessage();
            }
            catch (PasswordException e)
            {
                return e.getMessage();
            }
            catch (MailException e)
            {
                return e.getMessage();
            }
            catch (PhoneException e)
            {
                return e.getMessage();
            }
            catch (Exception e)
            {
                return "Connection error !";
            }

        }

        @Override
        protected void onPostExecute(String signUp)
        {
            Toast.makeText(SignUp.this, signUp, Toast.LENGTH_SHORT).show();
        }
    }

}
