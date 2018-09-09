package com.nikos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchMediaController {

    @RequestMapping(value = "/searchMedia", method = RequestMethod.POST)
    public ModelAndView searchMediaView(@RequestParam (value = "search", required = false) String search) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("searchMediaIndex");

        if (search == null)
            search = "";

        modelAndView.addObject("searchParam", search);
        return modelAndView;
    }
}
