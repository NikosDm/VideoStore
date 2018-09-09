package com.nikos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikos.model.Platform;
import com.nikos.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikos on 9/4/2018.
 */
@Service
public class PlatformService {

    @Autowired
    private PlatformRepository platformRepository;

    public Platform updatePlatform(String platformJSON){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Platform platform = objectMapper.readValue(platformJSON, Platform.class);

            if (platform.getPlatformID() == 0) {
                platform = platformRepository.insertNewPlatform(platform);
            }
            else {
                platform = platformRepository.updateSelectedPlatform(platform);
            }

            return platform;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<Platform> updatePlatformList(String platformListJSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Platform> platformList = objectMapper.readValue(platformListJSON, new TypeReference<List<Platform>>(){});
            Platform updatedPlatform;
            List<Platform> newPlatformList = new ArrayList<>();

            for (Platform platform: platformList){
                if (platform.getPlatformID() == 0) {
                    updatedPlatform = platformRepository.insertNewPlatform(platform);
                }
                else {
                    updatedPlatform = platformRepository.updateSelectedPlatform(platform);
                }
                newPlatformList.add(updatedPlatform);
            }

            return newPlatformList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedPlatform(int PlatformID) {
        try {
            return platformRepository.deleteSelectedPlatform(PlatformID);
        }
        catch (Exception ex){
            return 0;
        }
    }
}
