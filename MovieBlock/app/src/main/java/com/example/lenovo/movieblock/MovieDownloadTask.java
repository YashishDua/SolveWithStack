package com.example.lenovo.movieblock;

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
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lenovo on 28-06-2016.
 */
public class MovieDownloadTask extends AsyncTask<String , Integer , Movie[]> {

    MainActivity mactivity;
   /*public interface MovieDownloadTaskInterface{
        void processResults(Movie[] movies);
    }

    MovieDownloadTaskInterface listener;
    public MovieDownloadTask(MovieDownloadTaskInterface listener) {
        this.listener  = listener;
    }*/

    @Override
    protected void onPreExecute() {
        mactivity.progressBar = (ProgressBar)mactivity.findViewById(R.id.progress);
        mactivity.progressBar.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    public MovieDownloadTask(MainActivity mactivity) {
        this.mactivity = mactivity;
    }


    @Override
    protected Movie[] doInBackground(String... params) {
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

    private Movie[] parseJson(String json){
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("results");

            Movie[] output = new Movie[array.length()];
            for(int i = 0; i<array.length() ; i++){

                JSONObject movieobject = array.getJSONObject(i);
                int id =  movieobject.getInt("id");
                String poster_path = movieobject.getString("poster_path");
                String releasedate = movieobject.getString("release_date");
                String title = movieobject.getString("title");
                output[i] = new Movie(poster_path,title,id,releasedate);

                

            }
            return output;

        } catch (JSONException e) {
            return null;
        }

    }

    @Override
    protected void onPostExecute(Movie[] movies) {

        mactivity.progressBar.setVisibility(View.INVISIBLE);

        mactivity.processResults(movies);
    }
}
