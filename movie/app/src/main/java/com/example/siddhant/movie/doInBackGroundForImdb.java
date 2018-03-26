package com.example.siddhant.movie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ABC on 29-06-2016.
 */
public class doInBackGroundForImdb extends AsyncTask<String,Void,SpecificMovie>{
    MovieActivity movieActivity;

    public doInBackGroundForImdb(MovieActivity movieActivity) {
        this.movieActivity = movieActivity;
    }

    @Override
    protected SpecificMovie doInBackground(String... params) {
        if(params.length==0)
        {
            return null;
        }
        StringBuffer buffer=new StringBuffer();
        try {
            URL url=new URL(params[0]);
            HttpURLConnection urlConnection =
                    (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            if(inputStream==null)
            {
                return null;
            }
            Scanner s=new Scanner(inputStream);
            while (s.hasNext())
            {
                buffer.append(s.nextLine());
            }
            Log.i("buffer",buffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("imdb",buffer.toString());


        return parseJson(buffer.toString());
    }

    private SpecificMovie parseJson(String s) {
        try {
            JSONObject obj=new JSONObject(s);
            String imdbId=obj.getString("imdb_id");
            SpecificMovie movie=new SpecificMovie(imdbId);
            return movie;


        } catch (JSONException e) {
            return new SpecificMovie("");
        }
    }

    @Override
    protected void onPostExecute(SpecificMovie specificMovie) {
        movieActivity.progResult1(specificMovie);
    }
}
