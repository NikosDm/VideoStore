package com.nikos.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;

@Entity
@Table(name = "MovieCategory")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MovieCategoryID")
    private int MovieCategoryID = 0;

    @Column(name="MovieCategoryName", nullable = false, unique = true)
    private String MovieCategoryName = "";

    public MovieCategory() {
    }

    public MovieCategory(int MovieCategoryID){
        this.MovieCategoryID = MovieCategoryID;
        this.MovieCategoryName = "";
    }

    public int getMovieCategoryID() {
        return MovieCategoryID;
    }

    public String getMovieCategoryName() {
        return MovieCategoryName;
    }

    public void setMovieCategoryID(int movieCategoryID) {
        MovieCategoryID = movieCategoryID;
    }

    public void setMovieCategoryName(String movieCategoryName) {
        MovieCategoryName = movieCategoryName;
    }
}
