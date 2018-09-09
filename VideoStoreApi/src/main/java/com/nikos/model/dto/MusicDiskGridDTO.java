package com.nikos.model.dto;

import com.nikos.model.MusicDisk;
import com.nikos.model.MusicGenre;

import java.util.*;
import java.util.stream.Collectors;

public class MusicDiskGridDTO {

    private int MediaID = 0;
    private String MediaCode = "";
    private String MediaTitle = "";
    private String MediaDescription = "";
    private Date ReleaseDate = new Date();
    private byte[] MediaImage = new byte[0];
    private String MediaImageBase64 = "";
    private double Price = 0.0;
    private String Musician = "";
    private String MusicGenresNames = "";
    private List<String> musicGenres = new ArrayList<>();

    public MusicDiskGridDTO(MusicDisk musicDisk){
        MediaID = musicDisk.getMediaID();
        MediaCode = musicDisk.getMediaCode();
        MediaTitle = musicDisk.getMediaTitle();
        MediaDescription = musicDisk.getMediaDescription();
        ReleaseDate = musicDisk.getReleaseDate();
        MediaImage = musicDisk.getMediaImage();
        MediaImageBase64 = musicDisk.getMediaImageBase64().isEmpty() && musicDisk.getMediaImage().length != 0 ? "data:image/*;base64," + Base64.getEncoder().encodeToString(musicDisk.getMediaImage()) : musicDisk.getMediaImageBase64();
        Price = musicDisk.getPrice();
        Musician = musicDisk.getMusician();
        List<String> musicGenresList = (new ArrayList<>(Arrays.asList(musicDisk.getMusicGenres().toArray(new MusicGenre[musicDisk.getMusicGenres().size()]))))
                .stream()
                .map(musicGenre -> musicGenre.getGenreDescription())
                .collect(Collectors.toList());
        MusicGenresNames = String.join(", ", musicGenresList);
        musicGenres = (new ArrayList<>(Arrays.asList(musicDisk.getMusicGenres().toArray(new MusicGenre[musicDisk.getMusicGenres().size()]))))
                .stream()
                .map(musicGenre -> Integer.toString(musicGenre.getGenreID()))
                .collect(Collectors.toList());
    }

    public MusicDiskGridDTO() {}

    public int getMediaID() {
        return MediaID;
    }

    public void setMediaID(int mediaID) {
        MediaID = mediaID;
    }

    public String getMediaCode() {
        return MediaCode;
    }

    public void setMediaCode(String mediaCode) {
        MediaCode = mediaCode;
    }

    public String getMediaTitle() {
        return MediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        MediaTitle = mediaTitle;
    }

    public String getMediaDescription() {
        return MediaDescription;
    }

    public void setMediaDescription(String mediaDescription) {
        MediaDescription = mediaDescription;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public byte[] getMediaImage() {
        return MediaImage;
    }

    public void setMediaImage(byte[] mediaImage) {
        MediaImage = mediaImage;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getMusician() {
        return Musician;
    }

    public void setMusician(String musician) {
        Musician = musician;
    }

    public String getMusicGenresNames() {
        return MusicGenresNames;
    }

    public void setMusicGenresNames(String musicGenresNames) {
        MusicGenresNames = musicGenresNames;
    }

    public List<String> getMusicGenres() {
        return musicGenres;
    }

    public void setMusicGenres(List<String> musicGenres) {
        this.musicGenres = musicGenres;
    }

    public String getMediaImageBase64() {
        return MediaImageBase64;
    }

    public void setMediaImageBase64(String mediaImageBase64) {
        MediaImageBase64 = mediaImageBase64;
    }
}
