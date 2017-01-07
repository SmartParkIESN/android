package com.henallux.projet.smartpark.business;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.ParkingController;
import com.henallux.projet.smartpark.controller.PlaceController;
import com.henallux.projet.smartpark.exceptions.AdressException;
import com.henallux.projet.smartpark.exceptions.StreetException;
import com.henallux.projet.smartpark.exceptions.DescriptionException;
import com.henallux.projet.smartpark.exceptions.NumberException;
import com.henallux.projet.smartpark.exceptions.TitleException;
import com.henallux.projet.smartpark.modele.Parking;
import com.henallux.projet.smartpark.modele.Place;
import com.henallux.projet.smartpark.modele.User;

import java.util.ArrayList;

/**
 * Created by Lucas on 10/12/2016.
 */

public class NewParking extends AppCompatActivity {

    private Button CreateParkingBtt;
    private static ArrayList<Place> placesGlob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newparking);

        new loadPlaces().execute();

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
                Spinner editPlace = (Spinner)  findViewById(R.id.CreateAnnoucementPlace);

                TextView textView = (TextView)editPlace.getSelectedView();
                String placeString = textView.getText().toString();
                int i = 0;
                while(i < placesGlob.size() && !placesGlob.get(i).getName().equals(placeString))
                {
                    i++;
                }

                Parking parking = new Parking(editTitle.getText().toString(), editStreet.getText().toString(), editStreetNumber.getText().toString(), "1",  editDescription.getText().toString(), placesGlob.get(i).getPlaceId(), user.getUserId(), placesGlob.get(i), user);
                new postParking().execute(parking);


            }
        });

    }

    private class postParking extends AsyncTask<Parking, Void, String>
    {

        @Override
        protected String doInBackground(Parking... log) {
            ParkingController parkingController = new ParkingController();
            try
            {
               parkingController.postParking(log[0]);
                return "Successfull !";
            }
            catch (TitleException e)
            {
                return e.getMessage();
            }
            catch (DescriptionException e)
            {
                return e.getMessage();
            }
            catch (StreetException e)
            {
                return e.getMessage();
            }
            catch (NumberException e)
            {
                return e.getMessage();
            }
            catch (AdressException e)
            {
                return e.getMessage();
            }
            catch (Exception e)
            {
                return "Connection error !";
            }
        }

        @Override
        protected void onPostExecute(String msg)
        {
            Toast.makeText(NewParking.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    private class loadPlaces extends AsyncTask<String, Void, ArrayList<Place>>
    {

        @Override
        protected ArrayList<Place> doInBackground(String... log) {

            PlaceController placeController = new PlaceController();
            ArrayList<Place> places = new ArrayList<>();

            try
            {
                places = placeController.getPlaces();
                placesGlob = places;
            }
            catch (Exception e)
            {

            }
            return places;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places)
        {
            ArrayList<String> placesString = new ArrayList<>();

            for (int i = 0; i < places.size(); i++)
            {
                placesString.add(places.get(i).getName());
            }

            Spinner spinner = (Spinner) findViewById(R.id.CreateAnnoucementPlace);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(NewParking.this, android.R.layout.simple_spinner_item, placesString);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
        }
    }

}
