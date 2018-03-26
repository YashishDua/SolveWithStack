package com.example.lenovo.movieblock;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class movie_content_activity extends AppCompatActivity {
    TextView title;
    TextView tagline;
    TextView release_date;
    TextView overview;
    TextView popularity;
    public ProgressBar progressBar;
    ImageView img;
    String poster_path;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_content_activity);

        poster_path = getIntent().getStringExtra("poster_path");
        int id = getIntent().getIntExtra("batch_id",-1);
        Log.i("Movie" , "id"+id);
        
       // content_ListView = (ListView) findViewById(R.id.listview_moviecontent);
        if (id != -1) {


            MovieContentDownloadTask task = new MovieContentDownloadTask(this);
            task.execute("https://api.themoviedb.org/3/movie/" + id + "?api_key=062216a71e30eabddccb5172187e11a5");
        }
        else Toast.makeText(this,"Id not found",Toast.LENGTH_SHORT).show();
            //View v = LayoutInflater.from(context).inflate(R.layout.movie_content_item_view , parent , false);
       // Log.i("Movie","Data "+ data.getOriginal_title());



    }

    public void processResult(MovieContent movieContents) {
        if (movieContents == null) {
            Log.i("Movie","Moviecontent data is null");
            return;
        }
        Log.i("Moviedb","Data recieved from background");
        img = (ImageView) findViewById(R.id.imageview_moviewallpaper);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/" + poster_path).into(img);

       /* data.setId(movieContents.getId());
        data.setOriginal_title(movieContents.getOriginal_title());
        data.setOverview(movieContents.getOverview());
        data.setPopularity(movieContents.getPopularity());
        data.setRelease_date(movieContents.getRelease_date());
        data.setTagline(movieContents.getTagline());
*/
      /*  title = (TextView)findViewById(R.id.textview_title);
        tagline = (TextView)findViewById(R.id.textview_tagline);
        release_date = (TextView)findViewById(R.id.textview_releasedate);
        overview = (TextView)findViewById(R.id.textview_overview);
        popularity = (TextView)findViewById(R.id.textview_popularity);

        title.setText(movieContents.getOriginal_title());
        tagline.setText(movieContents.getTagline());
        release_date.setText(movieContents.getRelease_date());
        overview.setText(movieContents.getOverview());
        popularity.setText(movieContents.getPopularity());
        Log.i("Moviedb","Text assigned to text views");
*/



    }

}