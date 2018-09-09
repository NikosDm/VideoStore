package com.nikos.apicontroller;

import com.nikos.model.SearchMedia;
import com.nikos.service.SearchMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchMediaApiController {

    @Autowired
    private SearchMediaService searchMediaService;

    @RequestMapping(value = "/api/SearchMediaApi/SearchMedias", method = RequestMethod.GET)
    public ResponseEntity<List<SearchMedia>> searchMedias(@RequestParam String searchParam) {

        try {
            ArrayList<SearchMedia> searchMedias = searchMediaService.getMediasList(searchParam, "");
            return new ResponseEntity<>(searchMedias, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
