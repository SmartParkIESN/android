package com.henallux.projet.smartpark.business;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.ParkingController;
import com.henallux.projet.smartpark.modele.Parking;
import com.henallux.projet.smartpark.modele.Place;
import com.henallux.projet.smartpark.modele.User;

/**
 * Created by Lucas on 10/12/2016.
 */

public class NewParking extends AppCompatActivity {

    private Button CreateParkingBtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newparking);

        //CLICK CREATE NEW PARKING
        CreateParkingBtt = (Button) findViewById(R.id.createParkingButton);
        CreateParkingBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserConnected userConnected = new UserConnected().getINSTANCE();
                User user = userConnected.getUserConnected();

                EditText editTitle = (EditText) findViewById(R.id.CreateParkingTitle);
                EditText editDescription = (EditText) findViewById(R.id.CreateParkingDescription);
                EditText editStreet = (EditText) findViewById(R.id.CreateParkingStreet);
                EditText editStreetNumber = (EditText) findViewById(R.id.CreateParkingNumber);


                Place place = new Place(1, "Saint-Servais");
                Parking parking = new Parking(editTitle.getText().toString(), editStreet.getText().toString(), editStreetNumber.getText().toString(), "none",  editDescription.getText().toString(), place.getId(), user.getId(), place, user);
                new postParking().execute(parking);


            }
        });

    }

    private class postParking extends AsyncTask<Parking, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(Parking... log) {

            Boolean heuuu = false;
            ParkingController parkingController = new ParkingController();
            try
            {
               parkingController.postParking(log[0]);
            }
            catch (Exception e)
            {

            }
            return heuuu;
        }

        @Override
        protected void onPostExecute(Boolean heuuu)
        {

        }
    }

}
