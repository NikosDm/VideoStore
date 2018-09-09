package com.nikos.model;

import com.nikos.model.dto.VideoGameDTO;
import com.nikos.model.dto.VideoGameGridDTO;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "VideoGame")
@PrimaryKeyJoinColumn(name="MediaID")
public class VideoGame extends Media{

    @Column(name="VideoGameMedia", nullable = false)
    private String VideoGameMedia = "";

    @Column(name="Manufacturer", nullable = false)
    private String Manufacturer = "";

    @Column(name="Publisher", nullable = false)
    private String Publisher = "";

    @Column(name="Developers", nullable = false)
    private String Developers = "";

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Platform> platforms = new HashSet<Platform>();

    public VideoGame(){ super(); }

    public VideoGame(VideoGameDTO videoGameDTO){
        super(videoGameDTO.getMediaID(), videoGameDTO.getMediaCode(), videoGameDTO.getMediaTitle(), videoGameDTO.getMediaDescription(), videoGameDTO.getReleaseDate(), videoGameDTO.getMediaImage(), videoGameDTO.getPrice(), videoGameDTO.getMediaImageBase64());
        VideoGameMedia = videoGameDTO.getVideoGameMedia();
        Manufacturer = videoGameDTO.getManufacturer();
        Publisher = videoGameDTO.getPublisher();
        Developers = videoGameDTO.getDevelopers();

        for (String language: videoGameDTO.getPlatforms()){
            this.platforms.add(new Platform(new Integer(language)));
        }
    }

    public VideoGame(VideoGameGridDTO videoGameGridDTO){
        super(videoGameGridDTO.getMediaID(), videoGameGridDTO.getMediaCode(), videoGameGridDTO.getMediaTitle(), videoGameGridDTO.getMediaDescription(), videoGameGridDTO.getReleaseDate(), videoGameGridDTO.getMediaImage(), videoGameGridDTO.getPrice(), videoGameGridDTO.getMediaImageBase64());
        VideoGameMedia = videoGameGridDTO.getVideoGameMedia();
        Manufacturer = videoGameGridDTO.getManufacturer();
        Publisher = videoGameGridDTO.getPublisher();
        Developers = videoGameGridDTO.getDevelopers();

        platforms = videoGameGridDTO.getPlatforms()
                .stream()
                .map(videoGameGrid -> new Platform(Integer.parseInt(videoGameGrid)))
                .collect(Collectors.toSet());
    }

    public String getDevelopers() {
        return Developers;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public String getVideoGameMedia() {
        return VideoGameMedia;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setDevelopers(String developers) {
        Developers = developers;
    }

    public void setVideoGameMedia(String videoGameMedia) {
        VideoGameMedia = videoGameMedia;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }
}
