package com.nikos.model.dto;

import com.nikos.model.Movie;
import com.nikos.model.MovieCategory;
import com.nikos.model.MovieLanguage;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieGridDTO {

    private int MediaID = 0;
    private String MediaCode = "";
    private String MediaTitle = "";
    private String MediaDescription = "";
    private Date ReleaseDate = new Date();
    private byte[] MediaImage = new byte[0];
    private String MediaImageBase64 = "";
    private double Price = 0.0;
    private String Director = "";
    private String Languages = "";
    private String Categories = "";
    private List<String> movieLanguages = new ArrayList<>();
    private List<String> movieCategories = new ArrayList<>();

    public MovieGridDTO() {}

    public MovieGridDTO(Movie movie){

        MediaID = movie.getMediaID();
        MediaCode = movie.getMediaCode();
        MediaTitle = movie.getMediaTitle();
        MediaDescription = movie.getMediaDescription();
        ReleaseDate = movie.getReleaseDate();
        MediaImage = movie.getMediaImage();
        MediaImageBase64 = movie.getMediaImageBase64().isEmpty() && movie.getMediaImage().length != 0 ? "data:image/*;base64," + Base64.getEncoder().encodeToString(movie.getMediaImage()) : movie.getMediaImageBase64();
        Price = movie.getPrice();
        Director = movie.getDirector();
        List<String> languagesList = (new ArrayList<>(Arrays.asList(movie.getMovieLanguage().toArray(new MovieLanguage[movie.getMovieLanguage().size()]))))
                .stream()
                .map(movieLanguage -> movieLanguage.getMovieLanguageName())
                .collect(Collectors.toList());
        List<String> categoriesList = (new ArrayList<>(Arrays.asList(movie.getMovieCategories().toArray(new MovieCategory[movie.getMovieCategories().size()]))))
                .stream()
                .map(movieCategory -> movieCategory.getMovieCategoryName())
                .collect(Collectors.toList());
        Languages = String.join(", ", languagesList);
        Categories = String.join(", ", categoriesList);
        movieLanguages = (new ArrayList<>(Arrays.asList(movie.getMovieLanguage().toArray(new MovieLanguage[movie.getMovieLanguage().size()]))))
                .stream()
                .map(movieLanguage -> Integer.toString(movieLanguage.getMovieLanguageID()))
                .collect(Collectors.toList());
        movieCategories = (new ArrayList<>(Arrays.asList(movie.getMovieCategories().toArray(new MovieCategory[movie.getMovieCategories().size()]))))
                .stream()
                .map(movieCategory -> Integer.toString(movieCategory.getMovieCategoryID()))
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

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
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

    public String getMediaImageBase64() {
        return MediaImageBase64;
    }

    public void setMediaImageBase64(String mediaImageBase64) {
        MediaImageBase64 = mediaImageBase64;
    }
}
