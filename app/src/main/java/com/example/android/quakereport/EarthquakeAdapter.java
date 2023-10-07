package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Store the recycled view in a variable listViewItem
        View listViewItem = convertView;

        // Check if the received view is null
        if (listViewItem == null) {
            // If it is null then inflate the layout
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        // Get the current Earthquake object
        Earthquake currentEarthquake = getItem(position);

        // Store the magnitude of the current earthquake in as a double
        double mag = currentEarthquake.getMagnitude();

        // Format the magnitude so that it always display one decimal place
        // Store the formatted magnitude in a String variable
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitude = formatter.format(mag);

        // Set the String magnitude to the Magnitude TextView in the item_list.xml
        TextView magnitudeText = listViewItem.findViewById(R.id.magnitude);
        magnitudeText.setText(magnitude);

        // Store the location of the earthquake in a String variable
        String location = currentEarthquake.getPlace();
        String primaryLocation;
        String locationOffset;

        // Split the String in two String: primary location and location offset
        // Here we used "of" as a String separator regex
        // Check if the location contains offset information or not
        // If it does, then store it in the location Offset String variable
        // If it does not, then store "Near the" in the location offset String variable
        if (location.contains("of")){
            primaryLocation = location.split("of")[1];
            locationOffset = location.split("of")[0];
            locationOffset += " of";
        } else {
            primaryLocation = location;
            locationOffset = "Near the";
        }

        // Set the primary location to the primary location TextView in the item_list.xml
        TextView primaryLocationText = listViewItem.findViewById(R.id.primary_location);
        primaryLocationText.setText(primaryLocation);

        // Set location offset to the location offset TextView in the item_list.xml
        TextView locationOffsetText = listViewItem.findViewById(R.id.location_offset);
        locationOffsetText.setText(locationOffset);

        // Create a date object to store the time of the earthquake.
        Date dateObject = new Date(currentEarthquake.getTimeInMiliseconds());

        // Get the Date from the current Earthquake object, format it and
        // Set it to the Date TextView in the item_list.xml
        TextView dateText = listViewItem.findViewById(R.id.date);
        dateText.setText(formatDate(dateObject));

        // Get the Time from the current Earthquake object, format it and
        // Set it to the Time TextView in the item_list.xml
        TextView timeText = listViewItem.findViewById(R.id.time);
        timeText.setText(formatTime(dateObject));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeText.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listViewItem;
    }

    private int getMagnitudeColor(double magnitude) {

        int magnitudeInt = (int) Math.floor(magnitude);
        int magnitudeColor;
        switch (magnitudeInt){
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 1:
            case 0:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return magnitudeColor;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
