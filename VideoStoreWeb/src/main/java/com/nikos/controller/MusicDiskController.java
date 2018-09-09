package com.nikos.controller;

import com.nikos.model.MusicGenre;
import com.nikos.service.MediaMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class MusicDiskController {

    @Autowired
    private MediaMetaDataService mediaMetaDataService;

    @RequestMapping(value = "/musicDisk/index")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView musicDiskIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("musicDiskIndex");
        return mv;
    }

    @RequestMapping(value = "/musicDisk/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView musicDiskEditIndex(@RequestParam int MusicDiskID){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("musicDiskEdit");
        ArrayList<MusicGenre> musicGenres = mediaMetaDataService.GetMusicGenres();
        mv.addObject("mediaID", MusicDiskID);
        mv.addObject("musicGenres", musicGenres);
        return mv;
    }
}
