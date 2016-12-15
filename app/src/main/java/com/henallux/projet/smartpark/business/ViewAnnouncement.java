package com.henallux.projet.smartpark.business;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.AnnoucementController;
import com.henallux.projet.smartpark.modele.Announcement;

import java.util.ArrayList;

public class ViewAnnouncement extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static Announcement announcementGlob = new Announcement();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewannoucement);

        Bundle bundle = this.getIntent().getExtras();

        String AnnouncementId = bundle.getString("id");
        new loadAnnoucement().execute(AnnouncementId);


    }

    private class loadAnnoucement extends AsyncTask<String, Void, Announcement>
    {

        @Override
        protected Announcement doInBackground(String... log) {

            Announcement announcement = new Announcement();
            AnnoucementController annoucementsController = new AnnoucementController();

            try
            {
                announcement = annoucementsController.getPlaceById(log[0]);
            }
            catch (Exception e)
            {

            }

            return announcement;
        }

        @Override
        protected void onPostExecute(Announcement announcement)
        {
            announcementGlob = announcement;

            TextView titleTextView =
                    (TextView) findViewById(R.id.ViewAnnoucementTitle);

            TextView descriptionTextView =
                    (TextView) findViewById(R.id.ViewAnnoucementDescription);

            TextView priceTextView =
                    (TextView) findViewById(R.id.ViewAnnoucementPrice);

            ImageView parkingImage =
                    (ImageView) findViewById(R.id.ViewAnnouncementParkingPhoto);

            titleTextView.setText(announcement.getTitle());
            descriptionTextView.setText(announcement.getParking().getDescription());
            priceTextView.setText("" + announcement.getPrice() + "eu/h");
            parkingImage.setImageResource(R.drawable.place);


            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(ViewAnnouncement.this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng parking = new LatLng(50.4673883, 4.871985399999971);
        mMap.addMarker(new MarkerOptions().position(parking).title(announcementGlob.getParking().getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(parking));
    }
}
