package com.nikos.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Immutable
@Table(name = "vMediaSearch")
public class SearchMedia {

    @Id
    @Column(name = "MediaID")
    private int mediaID = 0;

    @Column(name = "MediaCode")
    private String mediaCode = "";

    @Column(name = "MediaTitle")
    private String mediaTitle = "";

    @Column(name = "MediaDescription")
    private String mediaDescription = "";

    @Column(name = "ReleaseDate")
    private Date releaseDate = new Date();

    @Column(name = "MediaImage")
    private byte[] mediaImage = new byte[0];

    @Column(name = "Price")
    private double price = 0.0;

    @Column(name = "MediaType")
    private int mediaType = 0;

    @Transient
    private String MediaImageBase64 = "";

    public SearchMedia() {}

    public SearchMedia(int mediaID, String mediaCode, String mediaTitle, String mediaDescription, Date releaseDate, byte[] mediaImage, double price, int mediaType) {
        this.mediaID = mediaID;
        this.mediaCode = mediaCode;
        this.mediaTitle = mediaTitle;
        this.mediaDescription = mediaDescription;
        this.releaseDate = releaseDate;
        this.mediaImage = mediaImage;
        this.price = price;
        this.mediaType = mediaType;
    }

    public int getMediaID() {
        return mediaID;
    }

    public void setMediaID(int mediaID) {
        this.mediaID = mediaID;
    }

    public String getMediaCode() {
        return mediaCode;
    }

    public void setMediaCode(String mediaCode) {
        this.mediaCode = mediaCode;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getMediaDescription() {
        return mediaDescription;
    }

    public void setMediaDescription(String mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public byte[] getMediaImage() {
        return mediaImage;
    }

    public void setMediaImage(byte[] mediaImage) {
        this.mediaImage = mediaImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaImageBase64() {
        return MediaImageBase64;
    }

    public void setMediaImageBase64(String mediaImageBase64) {
        MediaImageBase64 = mediaImageBase64;
    }
}
