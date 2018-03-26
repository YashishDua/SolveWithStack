package com.example.lenovo.movieblock;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Lenovo on 28-06-2016.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

       Context context;
       ArrayList<Movie> data;
    public MovieAdapter(Context context, ArrayList<Movie> objects) {
        super(context, 0, objects);
        this.context = context;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView ;
        if(v==null) {
            v = LayoutInflater.from(context).inflate(R.layout.movie_list_item_view, parent, false);
        }
        Log.i("Movie","Data is entered to adapter");

          //  TextView movie_title = (TextView) v.findViewById(R.id.textview_moviename);
        //    TextView movie_release_date = (TextView)v.findViewById(R.id.textview_releasedate);
       // TextView movie_popularity = (TextView)v.findViewById(R.id.textview_popularity);
            ImageView movie_poster = (ImageView)v.findViewById(R.id.imageview_movieposter);
        Log.i("Movie","Id alloted inside adapter");

        Movie CurrentMovie = data.get(position);
         //   movie_title.setText(CurrentMovie.getTitle());
         //   movie_release_date.setText(CurrentMovie.getReleasedate());
       //     movie_popularity.setText(CurrentMovie.getPopularity());
            //ImageDownloadTask task = new ImageDownloadTask(movie_poster);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/" + CurrentMovie.getPoster_path()).into(movie_poster);
        //task.execute("http://image.tmdb.org/t/p/w342"+CurrentMovie.getPoster_path());
            Log.i("Movie","Data alloted inside Adapter");




        return  v;



        }
    }

