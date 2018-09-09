package com.nikos.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;

@Entity
@Table(name = "MusicGenre")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MusicGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GenreID")
    private int GenreID = 0;

    @Column(name="GenreDescription", nullable = false)
    private String GenreDescription = "";

    public MusicGenre() {}

    public MusicGenre(int genreID) {
        GenreID = genreID;
    }

    public int getGenreID() {
        return GenreID;
    }

    public void setGenreID(int genreID) {
        GenreID = genreID;
    }

    public String getGenreDescription() {
        return GenreDescription;
    }

    public void setGenreDescription(String genreDescription) {
        GenreDescription = genreDescription;
    }
}
