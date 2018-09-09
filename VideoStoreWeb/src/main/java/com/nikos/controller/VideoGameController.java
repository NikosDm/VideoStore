package com.nikos.controller;

import com.nikos.model.Platform;
import com.nikos.service.MediaMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class VideoGameController {

    @Autowired
    private MediaMetaDataService mediaMetaDataService;

    @RequestMapping(value = "/videoGame/index")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView videoGamesIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("videoGameIndex");
        return mv;
    }

    @RequestMapping(value = "/videoGame/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView videoGamesEditIndex(@RequestParam int VideoGameID){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("videoGameEdit");
        ArrayList<Platform> platforms = mediaMetaDataService.GetVideoGamePlatforms();
        mv.addObject("mediaID", VideoGameID);
        mv.addObject("platforms", platforms);
        return mv;
    }
}
