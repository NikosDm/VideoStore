package com.nikos.service;

import com.nikos.model.SearchMedia;
import com.nikos.repository.SearchMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;

@Service
public class SearchMediaService {

    @Autowired
    private SearchMediaRepository searchMediaRepository;

    public ArrayList<SearchMedia> getMediasList(String WhereClause, String OrderClause){

        try {
            ArrayList<SearchMedia> searchMedias = searchMediaRepository.getMediaList(WhereClause, OrderClause);
            searchMedias.forEach(
                    searchMedia -> {
                        String imageBase64 = searchMedia.getMediaImage().length != 0 ? "data:image/*;base64," + Base64.getEncoder().encodeToString(searchMedia.getMediaImage()) : "";
                        searchMedia.setMediaImageBase64(imageBase64);
                    }
            );
            return searchMedias;
        }
        catch (Exception ex){
            return new ArrayList<>();
        }
    }
}
