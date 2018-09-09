package com.nikos.apicontroller;

import com.nikos.model.MusicDisk;
import com.nikos.model.dto.MusicDiskDTO;
import com.nikos.model.dto.MusicDiskGridDTO;
import com.nikos.service.MediaDataService;
import com.nikos.service.MusicDiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nikos on 8/21/2018.
 */
@RestController
public class MusicDiskApiController {

    @Autowired
    private MusicDiskService musicDiskService;

    @Autowired
    private MediaDataService mediaDataService;

    @RequestMapping(value = "/api/MusicDiskApi/ListMusicDisks", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<MusicDiskGridDTO>> getMusicDisksList(){

        try {
            ArrayList<MusicDisk> movieList = musicDiskService.getMusicDisks();
            ArrayList<MusicDiskGridDTO> movieGridDTOs = new ArrayList<>(movieList
                    .stream()
                    .map(movie -> new MusicDiskGridDTO(movie))
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(movieGridDTOs, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MusicDiskApi/GetMusicDisk", method = RequestMethod.GET)
    public ResponseEntity<MusicDiskDTO> getMusicDiskByID(@RequestParam int MediaID){

        try {
            MusicDisk selectedMusicDisk = musicDiskService.getMusicDiskByID(MediaID);
            MusicDiskDTO fixedMusicDisk = new MusicDiskDTO(selectedMusicDisk);
            return new ResponseEntity<>(fixedMusicDisk, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MusicDiskDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MusicDiskApi/UpdateMusicDisks", method = RequestMethod.PUT)
    public ResponseEntity<List<MusicDiskGridDTO>> updateMusicDisks(@RequestBody String selectedMusicDisksList){

        try {
            List<MusicDisk> fixedMusicDiskList = mediaDataService.readListMusicDiskGridDataFromJSON(selectedMusicDisksList);
            fixedMusicDiskList = musicDiskService.updateSelectedMusicDisks(fixedMusicDiskList);
            ArrayList<MusicDiskGridDTO> movieGridDTOs = new ArrayList<>(fixedMusicDiskList
                    .stream()
                    .map(movie -> new MusicDiskGridDTO(movie))
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(movieGridDTOs, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MusicDiskApi/UpdateMusicDisk", method = RequestMethod.POST)
    public ResponseEntity<MusicDiskDTO> updateMusicDisk(@RequestBody String selectedMusicDiskJSON){

        try {
            MusicDisk selectedMusicDisk = mediaDataService.readMusicDiskDataFromJSON(selectedMusicDiskJSON);
            MusicDiskDTO selectedFixedMusicDisk = musicDiskService.updateSelectedMusicDisk(selectedMusicDisk);
            return new ResponseEntity<>(selectedFixedMusicDisk, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MusicDiskDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MusicDiskApi/UpdateGridMusicDisk", method = RequestMethod.POST)
    public ResponseEntity<MusicDiskGridDTO> updateGridMusicDisk(@RequestBody String selectedMusicDiskJSON){

        try {
            MusicDisk selectedMusicDisk = mediaDataService.readMusicDiskGridDataFromJSON(selectedMusicDiskJSON);
            MusicDiskGridDTO selectedFixedMusicDisk = musicDiskService.updateSelectedMusicDiskFromGrid(selectedMusicDisk);
            return new ResponseEntity<>(selectedFixedMusicDisk, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MusicDiskGridDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MusicDiskApi/DeleteMusicDisk", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteMusicDisk(@RequestParam int MediaID){

        try {
            int DeletedMusicDiskID = musicDiskService.deleteSelectedMusicDisk(MediaID);
            return new ResponseEntity<>(DeletedMusicDiskID, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
