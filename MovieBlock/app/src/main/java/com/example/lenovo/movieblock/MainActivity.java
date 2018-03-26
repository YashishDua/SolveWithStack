package com.example.lenovo.movieblock;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity 
{
    ListView listview_movieblock;
    ArrayList<Movie> data;
    MovieAdapter adapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview_movieblock = (ListView) findViewById(R.id.listview_movieblock);
        data = new ArrayList<>();

        String urlString  = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=062216a71e30eabddccb5172187e11a5";
        MovieDownloadTask task = new MovieDownloadTask(this);
        task.execute(urlString);
        adapter = new MovieAdapter(this,data);
        listview_movieblock.setAdapter(adapter);



        listview_movieblock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, movie_content_activity.class);
                Movie clickedMovie = (Movie) parent.getAdapter().getItem(position);
                Log.i("Movie","id"+ clickedMovie.getId()+"");
                i.putExtra("batch_id", clickedMovie.getId());
                i.putExtra("poster_path", clickedMovie.getPoster_path());
                startActivity(i);
            }
        });




    }

     public void processResults(Movie[] movies){
         if(movies==null){
             Log.i("Movie","Data is null");
             return;
         }
         data.clear();
         for(Movie m : movies){
             data.add(m);
         }
         Log.i("Movie","Movies added to data");
          adapter.notifyDataSetChanged();

     }
    /*public void process(Bitmap bitmap){
        bitmapdata.clear();
                for(Bitmap b : bitmap){
                    bitmapdata.add(b);
                }
    }*/
}
