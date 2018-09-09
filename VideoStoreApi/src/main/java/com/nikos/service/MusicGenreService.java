package com.nikos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikos.model.MusicGenre;
import com.nikos.repository.MusicGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikos on 9/4/2018.
 */
@Service
public class MusicGenreService {

    @Autowired
    private MusicGenreRepository musicGenreRepository;

    public MusicGenre updateMusicGenre(String musicGenreJSON){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MusicGenre musicGenre = objectMapper.readValue(musicGenreJSON, MusicGenre.class);

            if (musicGenre.getGenreID() == 0) {
                musicGenre = musicGenreRepository.insertNewMusicGenre(musicGenre);
            }
            else {
                musicGenre = musicGenreRepository.updateSelectedMusicGenre(musicGenre);
            }

            return musicGenre;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<MusicGenre> updateMusicGenreList(String musicGenreListJSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<MusicGenre> musicGenreList = objectMapper.readValue(musicGenreListJSON, new TypeReference<List<MusicGenre>>(){});
            MusicGenre updatedMusicGenre;
            List<MusicGenre> newMusicGenreList = new ArrayList<>();

            for (MusicGenre musicGenre: musicGenreList){
                if (musicGenre.getGenreID() == 0) {
                    updatedMusicGenre = musicGenreRepository.insertNewMusicGenre(musicGenre);
                }
                else {
                    updatedMusicGenre = musicGenreRepository.updateSelectedMusicGenre(musicGenre);
                }
                newMusicGenreList.add(updatedMusicGenre);
            }

            return newMusicGenreList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedMusicGenre(int MusicGenreID) {
        try {
            return musicGenreRepository.deleteSelectedMusicGenre(MusicGenreID);
        }
        catch (Exception ex){
            return 0;
        }
    }
}
