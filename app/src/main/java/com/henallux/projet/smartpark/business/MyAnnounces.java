package com.henallux.projet.smartpark.business;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.controller.AnnoucementController;
import com.henallux.projet.smartpark.modele.Announcement;
import com.henallux.projet.smartpark.modele.User;

import java.util.ArrayList;

/**
 * Created by Lucas on 10/12/2016.
 */

public class MyAnnounces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myannounces);

        new loadMyAnnoucements().execute();

    }

    private class loadMyAnnoucements extends AsyncTask<String, Void, ArrayList<Announcement>>
    {

        @Override
        protected ArrayList<Announcement> doInBackground(String... log) {

            ArrayList<Announcement> annoucements = new ArrayList<>();
            AnnoucementController annoucementsController = new AnnoucementController();

            UserConnected userConnected = new UserConnected().getINSTANCE();
            User user = userConnected.getUserConnected();

            try
            {
                annoucements = annoucementsController.getAnnoucementByUserId(user.getUserId());
            }
            catch (Exception e)
            {

            }

            return annoucements;
        }

        @Override
        protected void onPostExecute(ArrayList<Announcement> annoucements)
        {
            ListView listAnnounces;
            listAnnounces = (ListView) findViewById(R.id.listMyAnnounces);
            final ArrayList<Announcement> annoucementslist = annoucements;

            AnnoucementsAdapter adapter = new AnnoucementsAdapter(MyAnnounces.this, annoucementslist);
            listAnnounces.setAdapter(adapter);

            listAnnounces.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // 1
                    Announcement selectedAnnouncement = annoucementslist.get(position);

                    // 2
                    Intent ViewAnnouncementIntent = new Intent(MyAnnounces.this, ViewAnnouncement.class);

                    // 3
                    ViewAnnouncementIntent.putExtra("id", "" + selectedAnnouncement.getId());

                    // 4
                    startActivity(ViewAnnouncementIntent);
                }

            });
        }
    }

}
