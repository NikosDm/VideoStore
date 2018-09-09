package com.nikos.service;

import com.nikos.model.VideoGame;
import com.nikos.model.dto.VideoGameDTO;
import com.nikos.model.dto.VideoGameGridDTO;
import com.nikos.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class VideoGameService {

    @Autowired
    private VideoGameRepository videoGameRepository;

    public ArrayList<VideoGame> getVideoGames() {
        try{
            List<VideoGame> videoGames = videoGameRepository.getMediaData("", "");
            return new ArrayList<>(videoGames);
        }
        catch (Exception ex){
            return null;
        }
    }

    public VideoGame getVideoGameByID(int VideoGameID){
        try {
            VideoGame selectedVideoGame = videoGameRepository.findMediaByMediaID(VideoGameID);
            return selectedVideoGame;
        }
        catch (Exception ex){
            return null;
        }
    }

    public VideoGameGridDTO updateSelectedVideoGameFromGrid(VideoGame videoGame){

        try {
            VideoGame selectedVideoGame;
            if (videoGame.getMediaID() != 0){
                selectedVideoGame = videoGameRepository.updateMediaData(videoGame);
            }
            else {
                selectedVideoGame = videoGameRepository.insertNewMediaData(videoGame);
            }

            VideoGameGridDTO videoGameGridDTO = new VideoGameGridDTO(selectedVideoGame);
            return videoGameGridDTO;
        }
        catch (Exception ex){
            return null;
        }
    }

    public VideoGameDTO updateSelectedVideoGame(VideoGame videoGame){

        try {
            VideoGame selectedVideoGame;
            if (videoGame.getMediaID() != 0){
                selectedVideoGame = videoGameRepository.updateMediaData(videoGame);
            }
            else {
                selectedVideoGame = videoGameRepository.insertNewMediaData(videoGame);
            }

            VideoGameDTO videoGameDTO = new VideoGameDTO(selectedVideoGame);
            return videoGameDTO;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<VideoGame> updateSelectedVideoGames(List<VideoGame> videoGamesList){
        try {
            ArrayList<VideoGame> newUpdatedVideoGames = new ArrayList<>();
            VideoGame updatedMovie;

            for (VideoGame selectedVideoGame: videoGamesList){

                if (selectedVideoGame.getMediaID() != 0){
                    updatedMovie = videoGameRepository.updateMediaData(selectedVideoGame);
                }
                else {
                    updatedMovie = videoGameRepository.insertNewMediaData(selectedVideoGame);
                }

                newUpdatedVideoGames.add(updatedMovie);
            }

            return newUpdatedVideoGames;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedVideoGame(int VideoGameID){
        try {
            return videoGameRepository.deleteMedia(VideoGameID);
        }
        catch(Exception ex){
            return 0;
        }
    }
}
