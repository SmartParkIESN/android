package com.henallux.projet.smartpark.business;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.AnnoucementController;
import com.henallux.projet.smartpark.modele.Annoucement;

import java.util.ArrayList;

/**
 * Created by Lucas on 07/12/2016.
 */

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        UserConnected userConnected = new UserConnected().getINSTANCE();
        View view = findViewById(R.id.activity_main);

        Snackbar snackbar = Snackbar.make(view, "Bienvenue " + userConnected.getUserConnected().getPseudo(), Snackbar.LENGTH_LONG);
        snackbar.show();


        new loadAnnoucements().execute();
    }

    private class loadAnnoucements extends AsyncTask<String, Void, ArrayList<Annoucement>>
    {

        @Override
        protected ArrayList<Annoucement> doInBackground(String... log) {

            ArrayList<Annoucement> annoucements = new ArrayList<>();
            AnnoucementController annoucementsController = new AnnoucementController();

            try
            {
                annoucements = annoucementsController.getAllAnnoucements();
            }
            catch (Exception e)
            {

            }

            return annoucements;
        }

        @Override
        protected void onPostExecute(ArrayList<Annoucement> annoucements)
        {
            ListView listAnnounces;
            listAnnounces = (ListView) findViewById(R.id.listAnnounces);

            ArrayAdapter<Annoucement> adapter = new ArrayAdapter<Annoucement>(Welcome.this, android.R.layout.simple_list_item_1, annoucements);
            listAnnounces.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.menu_Profil:
                intent = new Intent(Welcome.this, Profile.class);
                startActivity(intent);
                return true;
            case R.id.menu_logOut:
                UserConnected userConnected = new UserConnected().getINSTANCE();
                userConnected.setConnected(false);
                userConnected.setUser(null);

                intent = new Intent(Welcome.this, SignIn.class);
                startActivity(intent);
                return true;
            case R.id.menu_myAnnounce:
                intent = new Intent(Welcome.this, MyAnnounces.class);
                startActivity(intent);
                return true;
            case R.id.menu_createAnnouncement:
                intent = new Intent(Welcome.this, NewAnnoucement.class);
                startActivity(intent);
                return true;
            case R.id.menu_createParking:
                intent = new Intent(Welcome.this, NewParking.class);
                startActivity(intent);
                return true;
            case R.id.menu_search:
                intent = new Intent(Welcome.this, Search.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
