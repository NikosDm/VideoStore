package com.nikos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikos.model.MovieLanguage;
import com.nikos.repository.MovieLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikos on 9/4/2018.
 */
@Service
public class MovieLanguageService {

    @Autowired
    private MovieLanguageRepository movieLanguageRepository;

    public MovieLanguage updateMovieLanguage(String movieLanguageJSON){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MovieLanguage movieLanguage = objectMapper.readValue(movieLanguageJSON, MovieLanguage.class);

            if (movieLanguage.getMovieLanguageID() == 0) {
                movieLanguage = movieLanguageRepository.insertNewMovieLanguage(movieLanguage);
            }
            else {
                movieLanguage = movieLanguageRepository.updateSelectedMovieLanguage(movieLanguage);
            }

            return movieLanguage;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<MovieLanguage> updateMovieLanguageList(String movieLanguageListJSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<MovieLanguage> movieLanguageList = objectMapper.readValue(movieLanguageListJSON, new TypeReference<List<MovieLanguage>>(){});
            MovieLanguage updatedMovieLanguage;
            List<MovieLanguage> newMovieLanguageList = new ArrayList<>();

            for (MovieLanguage movieLanguage: movieLanguageList){
                if (movieLanguage.getMovieLanguageID() == 0) {
                    updatedMovieLanguage = movieLanguageRepository.insertNewMovieLanguage(movieLanguage);
                }
                else {
                    updatedMovieLanguage = movieLanguageRepository.updateSelectedMovieLanguage(movieLanguage);
                }
                newMovieLanguageList.add(updatedMovieLanguage);
            }

            return newMovieLanguageList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedMovieLanguage(int MovieLanguageID) {
        try {
            return movieLanguageRepository.deleteSelectedMovieLanguage(MovieLanguageID);
        }
        catch (Exception ex){
            return 0;
        }
    }
}
