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



            }
        });
    }

    private class logIn extends AsyncTask<String, Void, User>
    {
        @Override
        protected User doInBackground()
        {
            EditText editPseudo = (EditText) findViewById(R.id.loginPseudo);
            String pseudo = editPseudo.getText().toString();

            User user = new User();
            UserDAO userDao = new UserDAO();
            try
            {
                user = userDao.signIn(pseudo);
            }
            catch (Exception e)
            {
                Toast.makeText(SignIn.this, "Probleme ..", Toast.LENGTH_SHORT).show();
            }

            return user;
        }

        @Override
        protected void onPostExecute(User user)
        {
            Toast.makeText(SignIn.this, "Probleme ..", Toast.LENGTH_SHORT).show();
        }
    }

}

