package com.example.siddhant.movie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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
 * Created by ABC on 28-06-2016.
 */
public class doInBackground extends AsyncTask<String,Integer,MovieItem[]> {
    MainActivity mainActivity;
    public doInBackground(MainActivity activity) {
        mainActivity=activity;
    }

    @Override
    protected void onPreExecute() {
        mainActivity.progressBar = (ProgressBar)mainActivity.findViewById(R.id.progress);
        mainActivity.progressBar.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected MovieItem[] doInBackground(String... params) {
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


        return parseJson(buffer.toString());



    }

    private MovieItem[] parseJson(String s) {
        try {
            JSONObject obj=new JSONObject(s);
            JSONArray arr=obj.getJSONArray("results");
            MovieItem output[]=new MovieItem[arr.length()];
            for(int i=0;i<arr.length();i++)
            {
                JSONObject test=arr.getJSONObject(i);
                String url="http://image.tmdb.org/t/p/w342//"+test.getString("poster_path");
                try {
                    URL urlConnection = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) urlConnection
                            .openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    String Overview=test.getString("overview");
                    String Title=test.getString("title");
                    output[i]=new MovieItem(test.getInt("id"),myBitmap,Overview,Title);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            return output;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(MovieItem[] bitmaps) {
        mainActivity.progressBar.setVisibility(View.INVISIBLE);
        mainActivity.processResult(bitmaps);
    }
}
