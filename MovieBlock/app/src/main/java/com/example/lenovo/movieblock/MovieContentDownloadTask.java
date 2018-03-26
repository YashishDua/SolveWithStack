package com.example.lenovo.movieblock;

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
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lenovo on 28-06-2016.
 */
public class MovieContentDownloadTask extends AsyncTask<String , Integer , MovieContent>{

    movie_content_activity mactivity;
    public MovieContentDownloadTask(movie_content_activity activity) {
        this.mactivity = activity;
    }


    @Override
    protected void onPreExecute() {
        Log.i("Movie","Entered PreExecute");
        mactivity.progressBar = (ProgressBar)mactivity.findViewById(R.id.progress);
        mactivity.progressBar.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    @Override

    protected MovieContent doInBackground(String... params) {
        Log.i("Movie","Enterd DOINBackground");
        if (params.length == 0)
            return null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection =
                    (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());

            InputStream inputStream =
                    urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            Scanner s = new Scanner(inputStream);
            while (s.hasNext()) {
                buffer.append(s.nextLine());
            }
            Log.i("jsondata", buffer.toString());
        } catch (MalformedURLException e) {
            return null;
        } catch (ProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return parseJson(buffer.toString());

    }

    private MovieContent parseJson(String json){
        try {
            MovieContent tempobject;
            JSONObject object = new JSONObject(json);
                int id =  object.getInt("id");
                String overview = object.getString("overview");
                String release_date = object.getString("release_date");
                String original_title = object.getString("original_title");
                int popularity = object.getInt("popularity");
                String tagline = object.getString("tagline");
                tempobject = new MovieContent(original_title,overview,tagline,release_date,popularity,id);


            return tempobject;

        } catch (JSONException e) {
            return null;
        }


    }

    @Override
    protected void onPostExecute(MovieContent movieContent) {
        Log.i("Movie","Entered PostExecute");
        mactivity.progressBar.setVisibility(View.INVISIBLE);
        mactivity.processResult(movieContent);
    }
}
