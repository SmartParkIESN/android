package com.henallux.projet.smartpark.business;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.UserController;
import com.henallux.projet.smartpark.exceptions.LoginException;
import com.henallux.projet.smartpark.exceptions.PasswordException;
import com.henallux.projet.smartpark.exceptions.PseudoException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import static android.R.attr.duration;

public class SignIn extends Activity {

    private Button SignUpBtt;
    private Button SignInbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signin);


            //CLICK SIGN UP
            SignUpBtt = (Button) findViewById(R.id.signUpButton);
            SignUpBtt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SignUp.class);
                    startActivity(intent);
                }
            });

            //CLICK SIGN IN
            SignInbt = (Button) findViewById(R.id.signInButton);
            SignInbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText editPseudo = (EditText) findViewById(R.id.loginPseudo);
                    EditText editPassword = (EditText) findViewById(R.id.loginPassword);
                    String pseudo = editPseudo.getText().toString();
                    String password = editPassword.getText().toString();
                    new logIn().execute(pseudo, password);
                    Snackbar snackbar = Snackbar.make(view, "Connection...", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            });

    }

    private class logIn extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... log) {

            UserController userController = new UserController();
            try
            {
                userController.signIn(log[0], log[1]);
                return "";
            }
            catch (PseudoException e)
            {
                return e.getMessage();
            }
            catch (PasswordException e)
            {
                return e.getMessage();
            }
            catch (LoginException e)
            {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String connection)
        {
            if(connection != "")
            {
                Toast.makeText(SignIn.this, connection, Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent = new Intent(SignIn.this, Welcome.class);
                startActivity(intent);
            }
        }
    }

}

