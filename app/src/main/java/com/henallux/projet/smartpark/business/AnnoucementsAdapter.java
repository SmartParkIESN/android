package com.henallux.projet.smartpark.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.henallux.projet.smartpark.R;
import com.henallux.projet.smartpark.modele.Announcement;

import java.util.ArrayList;

/**
 * Created by Lucas on 15/12/2016.
 */

public class AnnoucementsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Announcement> mDataSource;

    public AnnoucementsAdapter(Context context, ArrayList<Announcement> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.listviewannounces, parent, false);

        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.listAnnouncesTitle);

// Get subtitle element
        TextView descriptionTextView =
                (TextView) rowView.findViewById(R.id.listAnnouncesDescription);

// Get detail element
        TextView priceTextView =
                (TextView) rowView.findViewById(R.id.listAnnouncesPrice);

// Get thumbnail element
        ImageView parkingImage =
                (ImageView) rowView.findViewById(R.id.listAnnouncesParkingPhoto);

        // 1
        Announcement announcement = (Announcement) getItem(position);

// 2
        titleTextView.setText(announcement.getTitle());
        descriptionTextView.setText(announcement.getParking().getDescription());
        priceTextView.setText("" + announcement.getPrice() + "eu/h");

        if(announcement.getParking().getPicture().equals("1"))
        {
            parkingImage.setImageResource(R.drawable.place);
        }
        else if(announcement.getParking().getPicture().equals("2"))
        {
            parkingImage.setImageResource(R.drawable.place2);
        }
        else
        {
            parkingImage.setImageResource(R.drawable.place3);
        }

        return rowView;
    }


}
