package com.example.siddhant.movie;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<MovieItem>imageArray=new ArrayList<>();
    imageAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=(GridView)findViewById(R.id.gridview);
        adapter=new imageAdapter(this,imageArray);
        gridView.setAdapter(adapter);
        String URL="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=9bc78cce9e9461c37071383709ac91a6";
        doInBackground blah=new doInBackground(this);
        blah.execute(URL);
        //item click listener
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent i=new Intent();
               MovieItem item=(MovieItem) parent.getAdapter().getItem(position);
               i.putExtra("id",item.getId());
               i.putExtra("Overview",item.getOverview());
               i.putExtra("Title", item.getTitle());

               i.setClass(MainActivity.this,MovieActivity.class);
               startActivity(i);
           }
       });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void processResult(MovieItem[] bitmaps) {
        if(bitmaps==null)
        {
            return;
        }
        imageArray.clear();
        for(MovieItem b:bitmaps)
        {
            imageArray.add(b);
        }
        adapter.notifyDataSetChanged();
    }
}
