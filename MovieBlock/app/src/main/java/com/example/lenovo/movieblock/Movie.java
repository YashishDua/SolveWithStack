package com.example.lenovo.movieblock;

/**
 * Created by Lenovo on 27-06-2016.
 */
public class Movie {
    String poster_path ;
    String title;
    int id;
    String releasedate ;
    //int popularity;

    public Movie(String poster_path, String title, int id, String releasedate) {
        this.poster_path = poster_path;
        this.title = title;
        this.id = id;
        this.releasedate = releasedate;
      //  this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

   // public int getPopularity(){return popularity;}

    public int getId() {
        return id;
    }

    public String getReleasedate() {
        return releasedate;
    }
}
