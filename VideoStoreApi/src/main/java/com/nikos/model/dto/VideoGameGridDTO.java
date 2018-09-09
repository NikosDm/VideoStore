package com.nikos.model.dto;

import com.nikos.model.Platform;
import com.nikos.model.VideoGame;

import java.util.*;
import java.util.stream.Collectors;

public class VideoGameGridDTO {

    private int MediaID = 0;
    private String MediaCode = "";
    private String MediaTitle = "";
    private String MediaDescription = "";
    private Date ReleaseDate = new Date();
    private byte[] MediaImage = new byte[0];
    private String MediaImageBase64 = "";
    private double Price = 0.0;
    private String VideoGameMedia = "";
    private String Manufacturer = "";
    private String Publisher = "";
    private String Developers = "";
    private String PlatformNames = "";
    private List<String> platforms = new ArrayList<>();

    public VideoGameGridDTO() {}

    public VideoGameGridDTO(VideoGame videoGame){
        MediaID = videoGame.getMediaID();
        MediaCode = videoGame.getMediaCode();
        MediaTitle = videoGame.getMediaTitle();
        MediaDescription = videoGame.getMediaDescription();
        ReleaseDate = videoGame.getReleaseDate();
        MediaImage = videoGame.getMediaImage();
        MediaImageBase64 = videoGame.getMediaImageBase64().isEmpty() && videoGame.getMediaImage().length != 0 ? "data:image/*;base64," + Base64.getEncoder().encodeToString(videoGame.getMediaImage()) : videoGame.getMediaImageBase64();
        Price = videoGame.getPrice();
        VideoGameMedia = videoGame.getVideoGameMedia();
        Manufacturer = videoGame.getManufacturer();
        Publisher = videoGame.getPublisher();
        Developers = videoGame.getDevelopers();
        List<String> platformsList = (new ArrayList<>(Arrays.asList(videoGame.getPlatforms().toArray(new Platform[videoGame.getPlatforms().size()]))))
                .stream()
                .map(platform -> platform.getPlatformDescription())
                .collect(Collectors.toList());
        PlatformNames = String.join(", ", platformsList);
        platforms = (new ArrayList<>(Arrays.asList(videoGame.getPlatforms().toArray(new Platform[videoGame.getPlatforms().size()]))))
                .stream()
                .map(platform -> Integer.toString(platform.getPlatformID()))
                .collect(Collectors.toList());
    }

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

    public String getVideoGameMedia() {
        return VideoGameMedia;
    }

    public void setVideoGameMedia(String videoGameMedia) {
        VideoGameMedia = videoGameMedia;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getDevelopers() {
        return Developers;
    }

    public void setDevelopers(String developers) {
        Developers = developers;
    }

    public String getPlatformNames() {
        return PlatformNames;
    }

    public void setPlatformNames(String platformNames) {
        PlatformNames = platformNames;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public String getMediaImageBase64() {
        return MediaImageBase64;
    }

    public void setMediaImageBase64(String mediaImageBase64) {
        MediaImageBase64 = mediaImageBase64;
    }
}
