package com.henallux.projet.smartpark.business;
import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.modele.User;
import com.henallux.projet.smartpark.DAO.UserDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {

    private Button SignUp;
    private Button SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        //CLICK SIGN UP
        SignUp = (Button) findViewById(R.id.signUpButton);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setContentView(R.layout.signup);
            }
        });

        //CLICK SIGN IN
        SignIn = (Button) findViewById(R.id.signInButton);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editPseudo = (EditText) findViewById(R.id.loginPseudo);
                EditText editPassword = (EditText) findViewById(R.id.loginPassword);
                String pseudo = editPseudo.getText().toString();
                String password = editPassword.getText().toString();

                new logIn().execute(pseudo, password);


            }
        });
    }

    private class logIn extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... log) {

            Boolean connection = false;
            UserDAO userDao = new UserDAO();
            try
            {
                connection = userDao.signIn(log[0], log[1]);
            }
            catch (Exception e)
            {

            }

            return connection;
        }

        @Override
        protected void onPostExecute(Boolean connection)
        {
            if(connection)
            {
                setContentView(R.layout.signup);
            }
            else
            {
                Toast.makeText(SignIn.this, "Connexion impossible", Toast.LENGTH_SHORT).show();
            }

        }
    }

}

