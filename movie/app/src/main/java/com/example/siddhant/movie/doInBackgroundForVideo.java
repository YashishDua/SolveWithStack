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
public class doInBackgroundForVideo extends AsyncTask<String,Void,Bitmap[]> {
    MovieActivity movieActivity;
    public doInBackgroundForVideo(MovieActivity activity) {
        movieActivity=activity;
    }

    @Override
    protected Bitmap[] doInBackground(String... params) {
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

    private Bitmap[] parseJson(String s) {
        try {
            JSONObject obj=new JSONObject(s);
            JSONArray arr=obj.getJSONArray("results");
            Bitmap bit[]=new Bitmap[arr.length()];

            String key=arr.getJSONObject(0).getString("key");
            String url="http://img.youtube.com/vi/"+key+"/hqdefault.jpg";
            for(int i=0;i<arr.length();i++)
            {
                try {
                    URL urlConnection = new URL(url);

                    HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    return my
                } catch (MalformedURLException e) {
                    return null;
                } catch (IOException e) {
                    return null;
                }


            }

        }
        catch (JSONException e) {
            return null;
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        movieActivity.progressBar1=(ProgressBar)movieActivity.findViewById(R.id.progress1);
        movieActivity.progressBar1.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap s) {
        movieActivity.progressBar1.setVisibility(View.INVISIBLE);
        movieActivity.processResult(s);
    }
}
