package com.henallux.projet.smartpark.business;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.UserController;
import com.henallux.projet.smartpark.modele.User;

/**
 * Created by Lucas on 10/12/2016.
 */

public class Profile extends AppCompatActivity {

    private Button Modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        UserConnected userConnected = new UserConnected().getINSTANCE();
        User user = userConnected.getUserConnected();

        EditText editPassword = (EditText) findViewById(R.id.profilePassword);
        EditText editPasswordConf = (EditText) findViewById(R.id.profilePassConf);
        EditText editMail = (EditText) findViewById(R.id.profileMail);
        EditText editPhone = (EditText) findViewById(R.id.profileTel);

        editPassword.setText(user.getPassword());
        editPasswordConf.setText(user.getPassword());
        editMail.setText(user.getEmail());
        editPhone.setText(user.getPhonenumber());

        //CLICK SIGN UP
        Modify = (Button) findViewById(R.id.modifyButton);
        Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserConnected userConnected = new UserConnected().getINSTANCE();
                User user = userConnected.getUserConnected();

                EditText editMail = (EditText) findViewById(R.id.profileMail);
                EditText editPassword = (EditText) findViewById(R.id.profilePassword);
                EditText editPasswordConf = (EditText) findViewById(R.id.profilePassConf);
                EditText editPhone = (EditText) findViewById(R.id.profileTel);

                if(editPassword.getText().toString().equals(editPasswordConf.getText().toString()))
                {
                    user.setEmail(editMail.getText().toString());
                    user.setPassword(editPassword.getText().toString());
                    user.setPhonenumber(editPhone.getText().toString());

                    new modifyUser().execute(user);
                }
                else
                {
                    Snackbar snackbar = Snackbar.make(view, "Passwords don't match !", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });


    }

    private class modifyUser extends AsyncTask<User, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(User... log) {

            Boolean modify = false;
            UserController userController = new UserController();
            try
            {
                modify = userController.modifyUser(log[0]);
            }
            catch (Exception e)
            {

            }

            return modify;
        }

        @Override
        protected void onPostExecute(Boolean modify)
        {
            if(modify)
            {
                Toast.makeText(Profile.this, "Ok", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Profile.this, "Modification impossible", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
