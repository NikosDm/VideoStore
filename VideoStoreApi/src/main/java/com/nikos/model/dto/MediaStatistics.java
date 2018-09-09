package com.nikos.model.dto;

public class MediaStatistics {

    private int videoGamesTotal = 0;

    private int musicDisksTotal = 0;

    private int moviesTotal = 0;

    public MediaStatistics() {}

    public MediaStatistics(int videoGamesTotal, int musicDisksTotal, int moviesTotal) {
        this.videoGamesTotal = videoGamesTotal;
        this.musicDisksTotal = musicDisksTotal;
        this.moviesTotal = moviesTotal;
    }

    public int getVideoGamesTotal() {
        return videoGamesTotal;
    }

    public void setVideoGamesTotal(int videoGamesTotal) {
        this.videoGamesTotal = videoGamesTotal;
    }

    public int getMusicDisksTotal() {
        return musicDisksTotal;
    }

    public void setMusicDisksTotal(int musicDisksTotal) {
        this.musicDisksTotal = musicDisksTotal;
    }

    public int getMoviesTotal() {
        return moviesTotal;
    }

    public void setMoviesTotal(int moviesTotal) {
        this.moviesTotal = moviesTotal;
    }
}
