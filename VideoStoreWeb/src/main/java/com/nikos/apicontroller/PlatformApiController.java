package com.nikos.apicontroller;

import com.nikos.model.Platform;
import com.nikos.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlatformApiController {

    @Autowired
    private PlatformService platformService;

    @RequestMapping(value = "/api/PlatformApi/updatePlatform", method = RequestMethod.POST)
    public ResponseEntity<Platform> updatePlatform (String platformJSON) {
        try {
            Platform platform = platformService.updatePlatform(platformJSON);
            return new ResponseEntity<>(platform, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new Platform(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/PlatformApi/updatePlatformList", method = RequestMethod.PUT)
    public ResponseEntity<List<Platform>> updatePlatformList (String platformListJSON) {
        try {
            List<Platform> platforms = platformService.updatePlatformList(platformListJSON);
            return new ResponseEntity<>(platforms, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/PlatformApi/deletePlatform", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deletePlatform (int SettingID) {
        try {
            int deletedPlatformID = platformService.deleteSelectedPlatform(SettingID);
            return new ResponseEntity<>(deletedPlatformID, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
