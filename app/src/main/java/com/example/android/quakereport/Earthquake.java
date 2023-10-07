package com.example.android.quakereport;

public class Earthquake {

    private final double mMagnitude;
    private final String mPlace;
    private final long mTimeInMiliseconds;
    private final String mUrl;

    public Earthquake(double magnitude, String place, long TimeInMiliseconds, String url){
        mMagnitude = magnitude;
        mPlace = place;
        mTimeInMiliseconds = TimeInMiliseconds;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public long getTimeInMiliseconds() {
        return mTimeInMiliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
