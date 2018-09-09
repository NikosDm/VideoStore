package com.nikos.service;

import com.nikos.model.MusicDisk;
import com.nikos.model.dto.MusicDiskDTO;
import com.nikos.model.dto.MusicDiskGridDTO;
import com.nikos.repository.MusicDiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class MusicDiskService {

    @Autowired
    private MusicDiskRepository musicDiskRepository;

    public ArrayList<MusicDisk> getMusicDisks() {
        try{
            List<MusicDisk> musicDisks = musicDiskRepository.getMediaData("", "");
            return new ArrayList<>(musicDisks);
        }
        catch (Exception ex){
            return null;
        }
    }

    public MusicDisk getMusicDiskByID(int MusicDiskID){
        try {
            MusicDisk selectedMusicDisk = musicDiskRepository.findMediaByMediaID(MusicDiskID);
            return selectedMusicDisk;
        }
        catch (Exception ex){
            return null;
        }
    }

    public MusicDiskGridDTO updateSelectedMusicDiskFromGrid(MusicDisk musicDisk){

        try {
            MusicDisk selectedMusicDisk;
            if (musicDisk.getMediaID() != 0){
                selectedMusicDisk = musicDiskRepository.updateMediaData(musicDisk);
            }
            else {
                selectedMusicDisk = musicDiskRepository.insertNewMediaData(musicDisk);
            }

            MusicDiskGridDTO movieGridDTO = new MusicDiskGridDTO(selectedMusicDisk);
            return movieGridDTO;
        }
        catch (Exception ex){
            return null;
        }
    }

    public MusicDiskDTO updateSelectedMusicDisk(MusicDisk musicDisk){

        try {
            MusicDisk selectedMusicDisk;
            if (musicDisk.getMediaID() != 0){
                selectedMusicDisk = musicDiskRepository.updateMediaData(musicDisk);
            }
            else {
                selectedMusicDisk = musicDiskRepository.insertNewMediaData(musicDisk);
            }

            MusicDiskDTO musicDiskDTO = new MusicDiskDTO(selectedMusicDisk);
            return musicDiskDTO;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<MusicDisk> updateSelectedMusicDisks(List<MusicDisk> musicDisksList){
        try {
            ArrayList<MusicDisk> newUpdatedMusicDisks = new ArrayList<>();
            MusicDisk updatedMusicDisk;

            for (MusicDisk selectedMusicDisk: musicDisksList){

                if (selectedMusicDisk.getMediaID() != 0){
                    updatedMusicDisk = musicDiskRepository.updateMediaData(selectedMusicDisk);
                }
                else {
                    updatedMusicDisk = musicDiskRepository.insertNewMediaData(selectedMusicDisk);
                }

                newUpdatedMusicDisks.add(updatedMusicDisk);
            }

            return newUpdatedMusicDisks;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedMusicDisk(int MusicDiskID){
        try {
            return musicDiskRepository.deleteMedia(MusicDiskID);
        }
        catch(Exception ex){
            return 0;
        }
    }
}
