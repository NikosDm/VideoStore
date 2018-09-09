package com.nikos.model.dto;

import com.nikos.model.Movie;
import com.nikos.model.MovieLanguage;
import com.nikos.model.MovieCategory;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieDTO {

    private int MediaID = 0;
    private String MediaCode = "";
    private String MediaTitle = "";
    private String MediaDescription = "";
    private Date ReleaseDate = new Date();
    private byte[] MediaImage = new byte[0];
    private String MediaImageBase64 = "";
    private double Price = 0.0;
    private String Director = "";
    private List<String> movieLanguages = new ArrayList<String>();
    private List<String> movieCategories = new ArrayList<String>();

    public MovieDTO() {}

    public MovieDTO(int mediaID, String mediaCode, String mediaTitle, String mediaDescription, Date releaseDate, byte[] mediaImage, double price, String director, List<String> movieLanguages, List<String> movieCategories) {
        this.MediaID = mediaID;
        this.MediaCode = mediaCode;
        this.MediaTitle = mediaTitle;
        this.MediaDescription = mediaDescription;
        this.ReleaseDate = releaseDate;
        this.MediaImage = mediaImage;
        this.MediaImageBase64 = Base64.getEncoder().encodeToString(mediaImage);
        this.Price = price;
        this.Director = director;
        this.movieLanguages = movieLanguages;
        this.movieCategories = movieCategories;
    }

    public MovieDTO(Movie movie){
        this.MediaID = movie.getMediaID();
        this.MediaCode = movie.getMediaCode();
        this.MediaTitle = movie.getMediaTitle();
        this.MediaDescription = movie.getMediaDescription();
        this.ReleaseDate = movie.getReleaseDate();
        this.MediaImage = movie.getMediaImage();
        this.MediaImageBase64 = movie.getMediaImageBase64().isEmpty() && movie.getMediaImage().length != 0 ? "data:image/*;base64," + Base64.getEncoder().encodeToString(movie.getMediaImage()) : movie.getMediaImageBase64();
        this.Price = movie.getPrice();
        this.Director = movie.getDirector();
        this.movieCategories = movie.getMovieCategories()
                .stream()
                .map(movieCategory -> Integer.toString(movieCategory.getMovieCategoryID()))
                .collect(Collectors.toList());
        this.movieLanguages = movie.getMovieLanguage()
                .stream()
                .map(language -> Integer.toString(language.getMovieLanguageID()))
                .collect(Collectors.toList());
    }

    public String getMediaDescription() {
        return MediaDescription;
    }

    public void setMediaDescription(String MediaDescription) {
        this.MediaDescription = MediaDescription;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public List<String> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(List<String> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }

    public List<String> getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(List<String> movieCategories) {
        this.movieCategories = movieCategories;
    }

    public int getMediaID() {

        return MediaID;
    }

    public void setMediaID(int MediaID) {
        this.MediaID = MediaID;
    }

    public String getMediaCode() {
        return MediaCode;
    }

    public void setMediaCode(String MediaCode) {
        this.MediaCode = MediaCode;
    }

    public String getMediaTitle() {
        return MediaTitle;
    }

    public void setMediaTitle(String MediaTitle) {
        this.MediaTitle = MediaTitle;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }

    public byte[] getMediaImage() {
        return MediaImage;
    }

    public void setMediaImage(byte[] MediaImage) {
        this.MediaImage = MediaImage;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getMediaImageBase64() {
        return MediaImageBase64;
    }

    public void setMediaImageBase64(String mediaImageBase64) {
        MediaImageBase64 = mediaImageBase64;
    }
}
