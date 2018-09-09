package com.nikos.model;

import com.nikos.model.dto.MusicDiskDTO;
import com.nikos.model.dto.MusicDiskGridDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "MusicDisk")
@PrimaryKeyJoinColumn(name = "MediaID")
public class MusicDisk extends Media {

    @Column(name="Musician", nullable = false)
    private String Musician = "";

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MusicGenre> musicGenres = new HashSet<>();

    public MusicDisk() {}
//
//    public MusicDisk(int mediaID, String mediaCode, String mediaTitle, String mediaDescription, Date releaseDate, byte[] mediaImage, double price, String musician, Set<MusicGenre> musicGenres) {
//        super(mediaID, mediaCode, mediaTitle, mediaDescription, releaseDate, mediaImage, price);
//        Musician = musician;
//        this.musicGenres = musicGenres;
//    }

    public MusicDisk(MusicDiskDTO musicDiskDTO){
        super(musicDiskDTO.getMediaID(), musicDiskDTO.getMediaCode(), musicDiskDTO.getMediaTitle(), musicDiskDTO.getMediaDescription(), musicDiskDTO.getReleaseDate(), musicDiskDTO.getMediaImage(), musicDiskDTO.getPrice(), musicDiskDTO.getMediaImageBase64());
        Musician = musicDiskDTO.getMusician();

        for (String language: musicDiskDTO.getMusicGenres()){
            this.musicGenres.add(new MusicGenre(new Integer(language)));
        }
    }

    public MusicDisk(MusicDiskGridDTO musicDiskGridDTO){
        super(musicDiskGridDTO.getMediaID(), musicDiskGridDTO.getMediaCode(), musicDiskGridDTO.getMediaTitle(), musicDiskGridDTO.getMediaDescription(), musicDiskGridDTO.getReleaseDate(), musicDiskGridDTO.getMediaImage(), musicDiskGridDTO.getPrice(), musicDiskGridDTO.getMediaImageBase64());
        Musician = musicDiskGridDTO.getMusician();

        musicGenres = musicDiskGridDTO.getMusicGenres()
                .stream()
                .map(musicDiskGrid -> new MusicGenre(Integer.parseInt(musicDiskGrid)))
                .collect(Collectors.toSet());
    }

    public String getMusician() {
        return Musician;
    }

    public void setMusician(String musician) {
        Musician = musician;
    }

    public Set<MusicGenre> getMusicGenres() {
        return musicGenres;
    }

    public void setMusicGenres(Set<MusicGenre> musicGenres) {
        this.musicGenres = musicGenres;
    }
}
