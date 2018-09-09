package com.nikos.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SettingsController {

    @RequestMapping(value = "/settings")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView settingsView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settingsIndex");
        return modelAndView;
    }
}
