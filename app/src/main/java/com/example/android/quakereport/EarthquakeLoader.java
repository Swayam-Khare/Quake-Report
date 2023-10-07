package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String mUrl = "";

    public EarthquakeLoader (Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        return QueryUtils.fetchEarthquakeData(mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
