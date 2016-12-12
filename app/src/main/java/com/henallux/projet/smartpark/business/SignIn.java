package com.henallux.projet.smartpark.business;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.UserController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class SignIn extends AppCompatActivity {

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
                }
            });

    }

    private class logIn extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... log) {

            Boolean connection = false;
            UserController userController = new UserController();
            try
            {
                connection = userController.signIn(log[0], log[1]);
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
                Intent intent = new Intent(SignIn.this, Welcome.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(SignIn.this, "Connexion impossible", Toast.LENGTH_SHORT).show();
            }

        }
    }

}

