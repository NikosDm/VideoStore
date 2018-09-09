package com.nikos.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nikos on 9/2/2018.
 */
@Controller
public class StatisticsController {

    @RequestMapping(value = "/statistics")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView statisticsView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("statisticsIndex");
        return modelAndView;
    }
}
