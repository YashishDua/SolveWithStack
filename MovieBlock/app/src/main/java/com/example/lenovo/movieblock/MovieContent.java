package com.example.lenovo.movieblock;

/**
 * Created by Lenovo on 28-06-2016.
 */
public class MovieContent {
    String original_title;
    String overview;
    String tagline;
    String release_date;
    int popularity;
    int id;

    public MovieContent(String original_title, String overview, String tagline, String release_date, int popularity,int id) {
        this.original_title = original_title;
        this.overview = overview;
        this.tagline = tagline;
        this.release_date = release_date;
        this.popularity = popularity;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getTagline() {
        return tagline;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getPopularity() {
        return popularity;
    }
}
