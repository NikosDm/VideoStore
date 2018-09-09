package com.nikos.apicontroller;

import com.nikos.model.MusicGenre;
import com.nikos.service.MusicGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MusicGenreApiController {

    @Autowired
    private MusicGenreService musicGenreService;

    @RequestMapping(value = "/api/MusicGenreApi/updateMusicGenre", method = RequestMethod.POST)
    public ResponseEntity<MusicGenre> updateMusicGenre (String musicGenreJSON) {
        try {
            MusicGenre musicGenre = musicGenreService.updateMusicGenre(musicGenreJSON);
            return new ResponseEntity<>(musicGenre, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MusicGenre(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MusicGenreApi/updateMusicGenreList", method = RequestMethod.PUT)
    public ResponseEntity<List<MusicGenre>> updateMusicGenreList (String musicGenreListJSON) {
        try {
            List<MusicGenre> musicGenres = musicGenreService.updateMusicGenreList(musicGenreListJSON);
            return new ResponseEntity<>(musicGenres, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MusicGenreApi/deleteMusicGenre", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteMusicGenre (int SettingID) {
        try {
            int deleteMusicGenreID = musicGenreService.deleteSelectedMusicGenre(SettingID);
            return new ResponseEntity<>(deleteMusicGenreID, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
