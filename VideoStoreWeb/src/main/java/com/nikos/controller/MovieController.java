package com.nikos.controller;

import com.nikos.model.MovieCategory;
import com.nikos.model.MovieLanguage;
import com.nikos.service.MediaMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class MovieController {

    @Autowired
    private MediaMetaDataService mediaMetaDataService;

    @RequestMapping(value = "/movie/index")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView movieIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("movieIndex");
        return mv;
    }

    @RequestMapping(value = "/movie/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView movieEditIndex(@RequestParam int MovieID){
        ModelAndView mv = new ModelAndView();
        ArrayList<MovieCategory> movieCategories = mediaMetaDataService.GetMovieCategories();
        ArrayList<MovieLanguage> movieLanguages = mediaMetaDataService.GetMovieLanguages();
        mv.setViewName("movieEdit");
        mv.addObject("mediaID", MovieID);
        mv.addObject("movieCategories", movieCategories);
        mv.addObject("movieLanguages", movieLanguages);
        return mv;
    }

}
