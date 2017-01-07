package com.henallux.projet.smartpark.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.modele.User;

/**
 * Created by Lucas on 10/12/2016.
 */

public class Search extends AppCompatActivity {

    private Button SearchBtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        UserConnected userConnected = new UserConnected().getINSTANCE();
        User user = userConnected.getUserConnected();


        //CLICK SIGN UP
        SearchBtt = (Button) findViewById(R.id.searchButton);
        SearchBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                EditText editPriceFrom = (EditText) findViewById(R.id.SearchPriceFrom);
                EditText editPriceTo = (EditText) findViewById(R.id.SearchPriceTo);

                String priceTo = editPriceFrom.getText().toString();
                String priceFrom = editPriceTo.getText().toString();

                try
                {

                    if(priceTo.equals("") || priceFrom.equals(""))
                    {
                        Toast.makeText(Search.this, "You must type a min & a max !", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        int priceMinInt = Integer.parseInt(priceTo);
                        int priceMaxInt = Integer.parseInt(priceFrom);

                        if(priceMinInt > priceMaxInt)
                        {
                            Toast.makeText(Search.this, "Min price < max price !", Toast.LENGTH_LONG).show();
                        }
                        else if(priceMinInt < 0)
                        {
                            Toast.makeText(Search.this, "Price must be => 0", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent ViewAnnouncementIntent = new Intent(Search.this, Welcome.class);

                            ViewAnnouncementIntent.putExtra("from", priceTo);
                            ViewAnnouncementIntent.putExtra("to", priceFrom);

                            startActivity(ViewAnnouncementIntent);
                        }
                    }
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(Search.this, "Price must be numeric", Toast.LENGTH_LONG).show();
                }

            }
        });




    }

}
