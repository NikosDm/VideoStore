package com.nikos.model.dto;

import com.nikos.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikos on 9/4/2018.
 */
public class SettingsData {

    private List<Platform> platforms = new ArrayList<>();

    private List<MovieCategory> movieCategories = new ArrayList<>();

    private List<MovieLanguage> movieLanguages = new ArrayList<>();

    private List<MusicGenre> musicGenres = new ArrayList<>();

    private List<StoreUserRole> userRoles = new ArrayList<>();

    public SettingsData() {}

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<MovieCategory> getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(List<MovieCategory> movieCategories) {
        this.movieCategories = movieCategories;
    }

    public List<MovieLanguage> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(List<MovieLanguage> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }

    public List<MusicGenre> getMusicGenres() {
        return musicGenres;
    }

    public void setMusicGenres(List<MusicGenre> musicGenres) {
        this.musicGenres = musicGenres;
    }

    public List<StoreUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<StoreUserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
