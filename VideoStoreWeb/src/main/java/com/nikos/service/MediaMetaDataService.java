package com.nikos.service;

import com.nikos.model.MovieCategory;
import com.nikos.model.MovieLanguage;
import com.nikos.model.MusicGenre;
import com.nikos.model.Platform;
import com.nikos.model.dto.SettingsData;
import com.nikos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by nikos on 8/10/2018.
 */
@Service
public class MediaMetaDataService {

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private MovieCategoryRepository movieCategoryRepository;

    @Autowired
    private MovieLanguageRepository movieLanguageRepository;

    @Autowired
    private MusicGenreRepository musicGenreRepository;

    @Autowired
    private StoreUserRoleRepository storeUserRoleRepository;

    public ArrayList<MovieCategory> GetMovieCategories(){

        try {
            ArrayList<MovieCategory> movieCategories = movieCategoryRepository.getMovieCategoryList();
            return movieCategories;
        }
        catch (Exception ex){
            return null;
        }
    }

    public ArrayList<Platform> GetVideoGamePlatforms() {

        try {
            ArrayList<Platform> platforms = platformRepository.getPlatformList();
            return platforms;
        }
        catch(Exception ex){
            return null;
        }
    }

    public ArrayList<MovieLanguage> GetMovieLanguages(){

        try {
            ArrayList<MovieLanguage> movieLanguages = movieLanguageRepository.GetMovieLanguagesList();
            return movieLanguages;
        }
        catch(Exception ex){
            return null;
        }
    }

    public ArrayList<MusicGenre> GetMusicGenres() {

        try {
            ArrayList<MusicGenre> musicGenres = musicGenreRepository.GetMusicGenresList();
            return musicGenres;
        }
        catch(Exception ex){
            return null;
        }
    }

    public SettingsData fetchSettingsData(String CurrentUsername) {

        try {
            SettingsData settingsData = new SettingsData();
            settingsData.setMovieCategories(movieCategoryRepository.getMovieCategoryList());
            settingsData.setMovieLanguages(movieLanguageRepository.GetMovieLanguagesList());
            settingsData.setPlatforms(platformRepository.getPlatformList());
            settingsData.setUserRoles(storeUserRoleRepository.getStoreUserRoleList(CurrentUsername));
            settingsData.setMusicGenres(musicGenreRepository.GetMusicGenresList());
            return settingsData;
        }
        catch (Exception ex){
            return null;
        }
    }
}
