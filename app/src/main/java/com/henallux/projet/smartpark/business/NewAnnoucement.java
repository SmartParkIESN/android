package com.henallux.projet.smartpark.business;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.henallux.projet.smartpark.DAO.PlaceDAO;
import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.AnnoucementController;
import com.henallux.projet.smartpark.controller.ParkingController;
import com.henallux.projet.smartpark.controller.PlaceController;
import com.henallux.projet.smartpark.exceptions.PriceException;
import com.henallux.projet.smartpark.exceptions.TitleException;
import com.henallux.projet.smartpark.modele.Announcement;
import com.henallux.projet.smartpark.modele.Parking;
import com.henallux.projet.smartpark.modele.Place;
import com.henallux.projet.smartpark.modele.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lucas on 10/12/2016.
 */

public class NewAnnoucement extends AppCompatActivity {

    private Button CreateAnnouncementBtt;
    private static ArrayList<Parking> parkingsGlob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newannoucement);

        new loadParkings().execute();

        //CLICK CREATE NEW ANNOUNCEMENT
        CreateAnnouncementBtt = (Button) findViewById(R.id.createAnnoucementButton);
        CreateAnnouncementBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserConnected userConnected = new UserConnected().getINSTANCE();
                User user = userConnected.getUserConnected();

                EditText editTitle = (EditText) findViewById(R.id.CreateAnnoucementTitle);
                EditText editPrice = (EditText) findViewById(R.id.CreateAnnoucementPrice);
                Spinner editParking = (Spinner)  findViewById(R.id.createAnnouncementParking);

                TextView textView = (TextView)editParking.getSelectedView();
                String parkingString = textView.getText().toString();

                int i = 0;
                while(i < parkingsGlob.size() && !parkingsGlob.get(i).getName().equals(parkingString))
                {
                    i++;
                }

                Date date = new Date();

                try
                {
                    int price = Integer.parseInt(editPrice.getText().toString());
                    Announcement announcement = new Announcement(editTitle.getText().toString(), price, date, date,  false, parkingsGlob.get(i).getId());
                    new postAnnouncement().execute(announcement);
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(NewAnnoucement.this, "Price must be numeric", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private class postAnnouncement extends AsyncTask<Announcement, Void, String>
    {

        @Override
        protected String doInBackground(Announcement... log) {

            AnnoucementController annoucementController = new AnnoucementController();
            try
            {
                annoucementController.postAnnouncement(log[0]);
                return "Sucessful !";
            }
            catch (TitleException e)
            {
                return e.getMessage();
            }
            catch (PriceException e)
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
            Toast.makeText(NewAnnoucement.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    private class loadParkings extends AsyncTask<String, Void, ArrayList<Parking>>
    {

        @Override
        protected ArrayList<Parking> doInBackground(String... log) {

            ParkingController parkingController = new ParkingController();
            ArrayList<Parking> parkings = new ArrayList<>();

            try
            {
                parkings = parkingController.getMyParkings();
                parkingsGlob = parkings;
            }
            catch (Exception e)
            {

            }
            return parkings;
        }

        @Override
        protected void onPostExecute(ArrayList<Parking> parkings)
        {
            ArrayList<String> parkingsString = new ArrayList<>();

            for (int i = 0; i < parkings.size(); i++)
            {
                parkingsString.add(parkings.get(i).getName());
            }

            Spinner spinner = (Spinner) findViewById(R.id.createAnnouncementParking);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(NewAnnoucement.this, android.R.layout.simple_spinner_item, parkingsString);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
        }
    }

}
