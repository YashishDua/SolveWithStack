package com.example.siddhant.movie;

import android.graphics.Bitmap;

/**
 * Created by ABC on 28-06-2016.
 */
public class MovieItem {
     int id;
    Bitmap b;
    String Overview;
    String Title;

    public MovieItem(int id, Bitmap b, String overview, String title) {
        this.id = id;
        this.b = b;
        Overview = overview;
        Title = title;
    }

    public String getOverview() {
        return Overview;
    }

    public String getTitle() {
        return Title;
    }

    public int getId() {
        return id;
    }

    public Bitmap getB() {
        return b;
    }
}
