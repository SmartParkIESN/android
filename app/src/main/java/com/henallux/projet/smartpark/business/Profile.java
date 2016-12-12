package com.henallux.projet.smartpark.business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.EditText;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.modele.User;

/**
 * Created by Lucas on 10/12/2016.
 */

public class Profile extends AppCompatActivity {

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

    }

}
