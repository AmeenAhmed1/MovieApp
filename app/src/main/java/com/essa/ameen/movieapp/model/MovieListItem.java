package com.essa.ameen.movieapp.model;

/**
 * Created by ameen on 30-Jan-18.
 */

public class MovieListItem {
    String movie_title;
    String movie_over_view;
    String release_date;
    String image_url;
    int movie_rate;


    public MovieListItem(String movie_title, String movie_over_view, String release_date, String image_url) {
        this.movie_title = movie_title;
        this.movie_over_view = movie_over_view;
        this.release_date = release_date;
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_over_view() {
        return movie_over_view;
    }

    public void setMovie_over_view(String movie_over_view) {
        this.movie_over_view = movie_over_view;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
