package com.nikos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikos.model.Movie;
import com.nikos.model.MusicDisk;
import com.nikos.model.VideoGame;
import com.nikos.model.dto.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;

/**
 * Created by nikos on 8/13/2018.
 */
@Service
public class MediaDataService {

    /**
     * MOVIE DATA SERVICE
     * */
    public Movie readMovieDataFromJSON(String movieJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MovieDTO movieRawData = objectMapper.readValue(movieJSON, MovieDTO.class);

            if (!movieRawData.getMediaImageBase64().isEmpty()){
                String encodedMediaImg = movieRawData.getMediaImageBase64().split(",")[1];
                movieRawData.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
            }

            return new Movie(movieRawData);
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<Movie> readListMovieGridDataFromJSON(String moviesJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<MovieGridDTO> moviesRawData = objectMapper.readValue(moviesJSON, new TypeReference<List<MovieGridDTO>>(){});
            moviesRawData.forEach(
                    movieGridDTO -> {
                        if (!movieGridDTO.getMediaImageBase64().isEmpty()){
                            String encodedMediaImg = movieGridDTO.getMediaImageBase64().split(",")[1];
                            movieGridDTO.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
                        }
                    }
            );
            List<Movie> movieList = moviesRawData
                    .stream()
                    .map(movieGridDTO -> new Movie(movieGridDTO))
                    .collect(Collectors.toList());
            return movieList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public Movie readMovieGridDataFromJSON(String movieJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MovieGridDTO movieRawData = objectMapper.readValue(movieJSON, MovieGridDTO.class);

            if (!movieRawData.getMediaImageBase64().isEmpty()){
                String encodedMediaImg = movieRawData.getMediaImageBase64().split(",")[1];
                movieRawData.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
            }

            Movie movie = new Movie(movieRawData);
            return movie;
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * VIDEO GAME DATA SERVICE
     * */
    public VideoGame readVideoGameDataFromJSON(String videoGameJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            VideoGameDTO videoGameRawData = objectMapper.readValue(videoGameJSON, VideoGameDTO.class);

            if (!videoGameRawData.getMediaImageBase64().isEmpty()){
                String encodedMediaImg = videoGameRawData.getMediaImageBase64().split(",")[1];
                videoGameRawData.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
            }

            return new VideoGame(videoGameRawData);
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<VideoGame> readListVideoGameGridDataFromJSON(String videoGamesJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<VideoGameGridDTO> videoGameRawData = objectMapper.readValue(videoGamesJSON, new TypeReference<List<VideoGameGridDTO>>(){});
            videoGameRawData.forEach(
                    videoGameGridDTO -> {
                        if (!videoGameGridDTO.getMediaImageBase64().isEmpty()){
                            String encodedMediaImg = videoGameGridDTO.getMediaImageBase64().split(",")[1];
                            videoGameGridDTO.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
                        }
                    }
            );
            List<VideoGame> videoGameList = videoGameRawData
                    .stream()
                    .map(videoGameGridDTO -> new VideoGame(videoGameGridDTO))
                    .collect(Collectors.toList());
            return videoGameList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public VideoGame readVideoGameGridDataFromJSON(String videoGameJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            VideoGameGridDTO videoGameRawData = objectMapper.readValue(videoGameJSON, VideoGameGridDTO.class);

            if (!videoGameRawData.getMediaImageBase64().isEmpty()){
                String encodedMediaImg = videoGameRawData.getMediaImageBase64().split(",")[1];
                videoGameRawData.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
            }

            VideoGame videoGame = new VideoGame(videoGameRawData);
            return videoGame;
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * MUSIC DISK DATA SERVICE
     * */
    public MusicDisk readMusicDiskDataFromJSON(String musicDiskJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MusicDiskDTO musicDiskRawData = objectMapper.readValue(musicDiskJSON, MusicDiskDTO.class);

            if (!musicDiskRawData.getMediaImageBase64().isEmpty()){
                String encodedMediaImg = musicDiskRawData.getMediaImageBase64().split(",")[1];
                musicDiskRawData.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
            }

            return new MusicDisk(musicDiskRawData);
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<MusicDisk> readListMusicDiskGridDataFromJSON(String musicDisksJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<MusicDiskGridDTO> musicDiskRawData = objectMapper.readValue(musicDisksJSON, new TypeReference<List<VideoGameGridDTO>>(){});
            musicDiskRawData.forEach(
                    musicDiskGridDTO -> {
                        if (!musicDiskGridDTO.getMediaImageBase64().isEmpty()){
                            String encodedMediaImg = musicDiskGridDTO.getMediaImageBase64().split(",")[1];
                            musicDiskGridDTO.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
                        }
                    }
            );
            List<MusicDisk> musicDiskList = musicDiskRawData
                    .stream()
                    .map(musicDiskGridDTO -> new MusicDisk(musicDiskGridDTO))
                    .collect(Collectors.toList());
            return musicDiskList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public MusicDisk readMusicDiskGridDataFromJSON(String videoGameJSON){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MusicDiskGridDTO musicDiskRawData = objectMapper.readValue(videoGameJSON, MusicDiskGridDTO.class);

            if (!musicDiskRawData.getMediaImageBase64().isEmpty()){
                String encodedMediaImg = musicDiskRawData.getMediaImageBase64().split(",")[1];
                musicDiskRawData.setMediaImage(Base64.getDecoder().decode(encodedMediaImg.getBytes(StandardCharsets.UTF_8)));
            }

            MusicDisk musicDisk = new MusicDisk(musicDiskRawData);
            return musicDisk;
        }
        catch (Exception ex){
            return null;
        }
    }
}
