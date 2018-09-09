package com.nikos.service;

import com.nikos.model.dto.MediaStatistics;
import com.nikos.model.dto.UserRoleStatistics;
import com.nikos.model.dto.VideoStoreStatistics;
import com.nikos.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public VideoStoreStatistics loadVideoStoreStatistics(){
        try {
            VideoStoreStatistics videoStoreStatistics = new VideoStoreStatistics();
            MediaStatistics mediaStatistics = statisticsRepository.loadMediaStatistics();
            List<UserRoleStatistics> userRoleStatisticsList = statisticsRepository.loadUserStatistics();
            videoStoreStatistics.setMediaStatistics(mediaStatistics);
            videoStoreStatistics.setUserRoleStatistics(userRoleStatisticsList);
            return videoStoreStatistics;
        }
        catch (Exception ex){
            return null;
        }
    }
}
