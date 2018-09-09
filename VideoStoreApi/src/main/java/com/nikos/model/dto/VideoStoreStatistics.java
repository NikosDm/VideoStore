package com.nikos.model.dto;

import java.util.List;

public class VideoStoreStatistics {

    private MediaStatistics mediaStatistics;

    private List<UserRoleStatistics> userRoleStatistics;

    public VideoStoreStatistics() {
    }

    public MediaStatistics getMediaStatistics() {
        return mediaStatistics;
    }

    public void setMediaStatistics(MediaStatistics mediaStatistics) {
        this.mediaStatistics = mediaStatistics;
    }

    public List<UserRoleStatistics> getUserRoleStatistics() {
        return userRoleStatistics;
    }

    public void setUserRoleStatistics(List<UserRoleStatistics> userRoleStatistics) {
        this.userRoleStatistics = userRoleStatistics;
    }
}
