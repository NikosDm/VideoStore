package com.nikos.apicontroller;


import com.nikos.model.MovieCategory;
import com.nikos.service.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieCategoryApiController {

    @Autowired
    private MovieCategoryService movieCategoryService;

    @RequestMapping(value = "/api/MovieCategoryApi/updateMovieCategory", method = RequestMethod.POST)
    public ResponseEntity<MovieCategory> updateMovieCategory (String movieCategoryJSON) {
        try {
            MovieCategory movieCategory = movieCategoryService.updateMovieCategory(movieCategoryJSON);
            return new ResponseEntity<>(movieCategory, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MovieCategory(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieCategoryApi/updateMovieCategoryList", method = RequestMethod.PUT)
    public ResponseEntity<List<MovieCategory>> updateMovieCategoryList (String movieCategoryListJSON) {
        try {
            List<MovieCategory> movieCategories = movieCategoryService.updateMovieCategoryList(movieCategoryListJSON);
            return new ResponseEntity<>(movieCategories, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieCategoryApi/deleteMovieCategory", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteMovieCategory (int SettingID) {
        try {
            int deletedMovieCategoryID = movieCategoryService.deleteSelectedMovieCategory(SettingID);
            return new ResponseEntity<>(deletedMovieCategoryID, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
