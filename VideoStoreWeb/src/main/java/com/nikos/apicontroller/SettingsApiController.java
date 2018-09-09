package com.nikos.apicontroller;

import com.nikos.model.dto.SettingsData;
import com.nikos.service.MediaMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nikos on 9/5/2018.
 */
@RestController
public class SettingsApiController {

    @Autowired
    private MediaMetaDataService mediaMetaDataService;

    @RequestMapping(value = "/api/SettingsApi/LoadSettings", method = RequestMethod.GET)
    public ResponseEntity<SettingsData> fetchSettingsData() {
        try {
            SettingsData settingsData = mediaMetaDataService.fetchSettingsData(SecurityContextHolder.getContext().getAuthentication().getName());
            return new ResponseEntity<>(settingsData, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new SettingsData(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
