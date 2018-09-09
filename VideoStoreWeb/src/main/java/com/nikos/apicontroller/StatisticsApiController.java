package com.nikos.apicontroller;

import com.nikos.model.dto.VideoStoreStatistics;
import com.nikos.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsApiController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/api/StatisticsApi/LoadStatistics", method = RequestMethod.GET)
    public ResponseEntity<VideoStoreStatistics> loadVideoStoreStatistics() {
        try{
            VideoStoreStatistics videoStoreStatistics = statisticsService.loadVideoStoreStatistics();
            return new ResponseEntity<>(videoStoreStatistics, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new VideoStoreStatistics(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
