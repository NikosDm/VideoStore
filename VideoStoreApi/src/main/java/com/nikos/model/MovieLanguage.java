package com.nikos.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;

@Entity
@Table(name = "MovieLanguage")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MovieLanguageID")
    private int MovieLanguageID = 0;

    @Column(name="MovieLanguageName", nullable = false, unique = true)
    private String MovieLanguageName = "";

    public MovieLanguage() { }

    public MovieLanguage(int MovieLanguageID){
        this.MovieLanguageID = MovieLanguageID;
        this.MovieLanguageName = "";
    }

    public int getMovieLanguageID() {
        return MovieLanguageID;
    }

    public void setMovieLanguageID(int movieLanguageID) {
        MovieLanguageID = movieLanguageID;
    }

    public String getMovieLanguageName() {
        return MovieLanguageName;
    }

    public void setMovieLanguageName(String movieLanguageName) {
        MovieLanguageName = movieLanguageName;
    }
}
