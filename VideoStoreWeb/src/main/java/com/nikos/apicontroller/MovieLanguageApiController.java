package com.nikos.apicontroller;

import com.nikos.model.MovieLanguage;
import com.nikos.service.MovieLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieLanguageApiController {

    @Autowired
    private MovieLanguageService movieLanguageService;

    @RequestMapping(value = "/api/MovieLanguageApi/updateMovieLanguage", method = RequestMethod.POST)
    public ResponseEntity<MovieLanguage> updateMovieLanguage (String movieLanguageJSON) {
        try {
            MovieLanguage movieLanguage = movieLanguageService.updateMovieLanguage(movieLanguageJSON);
            return new ResponseEntity<>(movieLanguage, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MovieLanguage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieLanguageApi/updateMovieLanguageList", method = RequestMethod.PUT)
    public ResponseEntity<List<MovieLanguage>> updateMovieLanguageList (String movieLanguageListJSON) {
        try {
            List<MovieLanguage> movieCategories = movieLanguageService.updateMovieLanguageList(movieLanguageListJSON);
            return new ResponseEntity<>(movieCategories, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieLanguageApi/deleteMovieLanguage", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteMovieLanguage (int SettingID) {
        try {
            int deletedMovieLanguageID = movieLanguageService.deleteSelectedMovieLanguage(SettingID);
            return new ResponseEntity<>(deletedMovieLanguageID, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
