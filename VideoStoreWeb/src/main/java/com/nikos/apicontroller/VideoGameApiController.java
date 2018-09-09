package com.nikos.apicontroller;

import com.nikos.model.VideoGame;
import com.nikos.model.dto.VideoGameDTO;
import com.nikos.model.dto.VideoGameGridDTO;
import com.nikos.service.MediaDataService;
import com.nikos.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VideoGameApiController {

    @Autowired
    private VideoGameService videoGameService;

    @Autowired
    private MediaDataService mediaDataService;

    @RequestMapping(value = "/api/VideoGameApi/ListVideoGames", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<VideoGameGridDTO>> getVideoGamesList(){

        try {
            ArrayList<VideoGame> videoGames = videoGameService.getVideoGames();
            ArrayList<VideoGameGridDTO> videoGameGridDTOs = new ArrayList<>(videoGames
                    .stream()
                    .map(videoGame -> new VideoGameGridDTO(videoGame))
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(videoGameGridDTOs, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/VideoGameApi/GetVideoGame", method = RequestMethod.GET)
    public ResponseEntity<VideoGameDTO> getVideoGameByID(@RequestParam int MediaID){

        try {
            VideoGame selectedVideoGame = videoGameService.getVideoGameByID(MediaID);
            VideoGameDTO fixedVideoGame = new VideoGameDTO(selectedVideoGame);
            return new ResponseEntity<>(fixedVideoGame, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new VideoGameDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/VideoGameApi/UpdateVideoGames", method = RequestMethod.PUT)
    public ResponseEntity<List<VideoGameGridDTO>> updateVideoGames(@RequestBody String selectedVideoGamesList){

        try {
            List<VideoGame> fixedVideoGameList = mediaDataService.readListVideoGameGridDataFromJSON(selectedVideoGamesList);
            fixedVideoGameList = videoGameService.updateSelectedVideoGames(fixedVideoGameList);
            ArrayList<VideoGameGridDTO> movieGridDTOs = new ArrayList<>(fixedVideoGameList
                    .stream()
                    .map(movie -> new VideoGameGridDTO(movie))
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(movieGridDTOs, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/VideoGameApi/UpdateVideoGame", method = RequestMethod.POST)
    public ResponseEntity<VideoGameDTO> updateVideoGame(@RequestBody String selectedVideoGameJSON){

        try {
            VideoGame selectedVideoGame = mediaDataService.readVideoGameDataFromJSON(selectedVideoGameJSON);
            VideoGameDTO selectedFixedVideoGame = videoGameService.updateSelectedVideoGame(selectedVideoGame);
            return new ResponseEntity<>(selectedFixedVideoGame, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new VideoGameDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/VideoGameApi/UpdateGridVideoGame", method = RequestMethod.POST)
    public ResponseEntity<VideoGameGridDTO> updateGridVideoGame(@RequestBody String selectedVideoGameJSON){

        try {
            VideoGame selectedVideoGame = mediaDataService.readVideoGameGridDataFromJSON(selectedVideoGameJSON);
            VideoGameGridDTO selectedFixedVideoGame = videoGameService.updateSelectedVideoGameFromGrid(selectedVideoGame);
            return new ResponseEntity<>(selectedFixedVideoGame, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new VideoGameGridDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/VideoGameApi/DeleteVideoGame", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteVideoGame(@RequestParam int MediaID){

        try {
            int DeletedVideoGameID = videoGameService.deleteSelectedVideoGame(MediaID);
            return new ResponseEntity<>(DeletedVideoGameID, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
