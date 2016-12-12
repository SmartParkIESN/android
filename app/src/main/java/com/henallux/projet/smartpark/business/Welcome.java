package com.henallux.projet.smartpark.business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.henallux.projet.smartpark.R;

/**
 * Created by Lucas on 07/12/2016.
 */

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

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
