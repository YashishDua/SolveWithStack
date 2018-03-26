package com.example.siddhant.movie;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MovieActivity extends AppCompatActivity {
    ImageView v;
    ProgressBar progressBar1;
    Button Imdb;
    String idmbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Imdb=(Button) findViewById(R.id.imdb);
        TextView Overview=(TextView)findViewById(R.id.movieItemText2);
        TextView Title=(TextView)findViewById(R.id.movieItemText1);
        Overview.setText(Overview.getText()+getIntent().getStringExtra("Overview"));
        Title.setText(Title.getText()+getIntent().getStringExtra("Title"));
        v=(ImageView)findViewById(R.id.video);
        int id=getIntent().getIntExtra("id",-1);
        if(id!=-1) {
            String URL = "https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=9bc78cce9e9461c37071383709ac91a6";
            doInBackgroundForVideo blah=new doInBackgroundForVideo(this);
            blah.execute(URL);
        }
        String url1="https://api.themoviedb.org/3/movie/"+getIntent().getIntExtra("id",-1)+"?api_key=9bc78cce9e9461c37071383709ac91a6";
        doInBackGroundForImdb blah1=new doInBackGroundForImdb(this);
        blah1.execute(url1);
        Imdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://www.imdb.com/title/"+idmbId+"/";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }

    public void processResult(Bitmap s) {
        if(s==null)
        {
            return;
        }
       v.setImageBitmap(s);


    }

    public void progResult1(final SpecificMovie specificMovie) {
        idmbId=specificMovie.getImdbId();




    }
}
