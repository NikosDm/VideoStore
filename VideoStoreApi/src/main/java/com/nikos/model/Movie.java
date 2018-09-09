package com.nikos.model;

import com.nikos.model.dto.MovieDTO;
import com.nikos.model.dto.MovieGridDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Base64;

@Entity
@Table(name = "Movie")
@PrimaryKeyJoinColumn(name = "MediaID")
public class Movie extends Media{

    @Column(name="Director", nullable = false)
    @NotEmpty(message = "Please enter Director of the Movie.")
    private String Director = "";

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MovieLanguage> movieLanguages = new HashSet<MovieLanguage>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MovieCategory> movieCategories = new HashSet<MovieCategory>();

    public Movie(){ super(); }

    public Movie(MovieDTO movieDTO){
        super(movieDTO.getMediaID(), movieDTO.getMediaCode(), movieDTO.getMediaTitle(), movieDTO.getMediaDescription(), movieDTO.getReleaseDate(), movieDTO.getMediaImage(), movieDTO.getPrice(), movieDTO.getMediaImageBase64());
        Director = movieDTO.getDirector();

        for (String language: movieDTO.getMovieLanguages()){
            this.movieLanguages.add(new MovieLanguage(new Integer(language)));
        }

        for (String category: movieDTO.getMovieCategories()){
            this.movieCategories.add(new MovieCategory(new Integer(category)));
        }
    }

    public Movie(MovieGridDTO movieGridDTO){
        super(movieGridDTO.getMediaID(), movieGridDTO.getMediaCode(), movieGridDTO.getMediaTitle(), movieGridDTO.getMediaDescription(), movieGridDTO.getReleaseDate(), movieGridDTO.getMediaImage(), movieGridDTO.getPrice(), movieGridDTO.getMediaImageBase64());
        Director = movieGridDTO.getDirector();
        movieLanguages = movieGridDTO.getMovieLanguages()
                .stream()
                .map(movieGrid -> new MovieLanguage(Integer.parseInt(movieGrid)))
                .collect(Collectors.toSet());
        movieCategories = movieGridDTO.getMovieCategories()
                .stream()
                .map(movieGrid -> new MovieCategory(Integer.parseInt(movieGrid)))
                .collect(Collectors.toSet());
    }

    public String getDirector() {
        return Director;
    }

    public Set<MovieLanguage> getMovieLanguage() {
        return movieLanguages;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public void setMovieLanguage(Set<MovieLanguage> movieLanguage) {
        movieLanguages = movieLanguage;
    }

    public Set<MovieCategory> getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(Set<MovieCategory> movieCategories) {
        this.movieCategories = movieCategories;
    }
}
