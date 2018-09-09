package com.nikos.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Media")
@Inheritance(strategy= InheritanceType.JOINED)
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MediaID")
    private int MediaID = 0;

    @Column(name="MediaCode", nullable = false, unique = true)
    @NotEmpty(message = "Please enter Code.")
    private String MediaCode = "";

    @Column(name="MediaTitle", nullable = false, unique = true)
    @NotEmpty(message = "Please enter Title.")
    private String MediaTitle = "";

    @Column(name="MediaDescription", nullable = false)
    private String MediaDescription = "";

    @Column(name="ReleaseDate", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ReleaseDate = new Date();

    @Column(name="MediaImage")
    private byte[] MediaImage = new byte[0];

    @Column(name="Price")
    private double Price = 0.0;

    @Transient
    private String MediaImageBase64 = "";

    public Media(int mediaID, String mediaCode, String mediaTitle, String mediaDescription, Date releaseDate, byte[] mediaImage, double price, String mediaImageBase64) {
        MediaID = mediaID;
        MediaCode = mediaCode;
        MediaTitle = mediaTitle;
        MediaDescription = mediaDescription;
        ReleaseDate = releaseDate;
        MediaImage = mediaImage;
        Price = price;
        MediaImageBase64 = mediaImageBase64;
    }

    public Media(){}

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getMediaID() {
        return MediaID;
    }

    public String getMediaCode() {
        return MediaCode;
    }

    public String getMediaDescription() {
        return MediaDescription;
    }

    public String getMediaTitle() {
        return MediaTitle;
    }

    public void setMediaCode(String mediaCode) {
        MediaCode = mediaCode;
    }

    public void setMediaID(int mediaID) {
        MediaID = mediaID;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setMediaDescription(String mediaDescription) {
        MediaDescription = mediaDescription;
    }

    public void setMediaTitle(String mediaTitle) {
        MediaTitle = mediaTitle;
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

    public String getMediaImageBase64() {
        return MediaImageBase64;
    }

    public void setMediaImageBase64(String mediaImageBase64) {
        MediaImageBase64 = mediaImageBase64;
    }
}
