package com.nikos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikos.model.MovieCategory;
import com.nikos.repository.MovieCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikos on 9/4/2018.
 */
@Service
public class MovieCategoryService {

    @Autowired
    private MovieCategoryRepository movieCategoryRepository;

    public MovieCategory updateMovieCategory(String movieCategoryJSON){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MovieCategory movieCategory = objectMapper.readValue(movieCategoryJSON, MovieCategory.class);

            if (movieCategory.getMovieCategoryID() == 0) {
                movieCategory = movieCategoryRepository.insertNewMovieCategory(movieCategory);
            }
            else {
                movieCategory = movieCategoryRepository.updateSelectedMovieCategory(movieCategory);
            }

            return movieCategory;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<MovieCategory> updateMovieCategoryList(String movieCategoryListJSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<MovieCategory> movieCategoryList = objectMapper.readValue(movieCategoryListJSON, new TypeReference<List<MovieCategory>>(){});
            MovieCategory updatedMovieCategory;
            List<MovieCategory> newMovieCategoryList = new ArrayList<>();

            for (MovieCategory movieCategory: movieCategoryList){
                if (movieCategory.getMovieCategoryID() == 0) {
                    updatedMovieCategory = movieCategoryRepository.insertNewMovieCategory(movieCategory);
                }
                else {
                    updatedMovieCategory = movieCategoryRepository.updateSelectedMovieCategory(movieCategory);
                }
                newMovieCategoryList.add(updatedMovieCategory);
            }

            return newMovieCategoryList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedMovieCategory(int MovieCategoryID) {
        try {
            return movieCategoryRepository.deleteSelectedMovieCategory(MovieCategoryID);
        }
        catch (Exception ex){
            return 0;
        }
    }
}
