package com.nikos.apicontroller;

import com.nikos.model.Movie;
import com.nikos.model.dto.MovieDTO;
import com.nikos.model.dto.MovieGridDTO;
import com.nikos.service.MediaDataService;
import com.nikos.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieApiController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MediaDataService mediaDataService;

    @RequestMapping(value = "/api/MovieApi/ListMovies", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<MovieGridDTO>> getMoviesList(){

        try {
            ArrayList<Movie> movieList = movieService.getMovies();
            ArrayList<MovieGridDTO> movieGridDTOs = new ArrayList<>(movieList
                    .stream()
                    .map(movie -> new MovieGridDTO(movie))
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(movieGridDTOs, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieApi/GetMovie", method = RequestMethod.GET)
    public ResponseEntity<MovieDTO> getMovieByID(@RequestParam int MediaID){

        try {
            Movie selectedMovie = movieService.getMovieByID(MediaID);
            MovieDTO fixedMovie = new MovieDTO(selectedMovie);
            return new ResponseEntity<>(fixedMovie, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MovieDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieApi/UpdateMovies", method = RequestMethod.PUT)
    public ResponseEntity<List<MovieGridDTO>> updateMovies(@RequestBody String selectedMoviesList){

        try {
            List<Movie> fixedMovieList = mediaDataService.readListMovieGridDataFromJSON(selectedMoviesList);
            fixedMovieList = movieService.updateSelectedMovies(fixedMovieList);
            ArrayList<MovieGridDTO> movieGridDTOs = new ArrayList<>(fixedMovieList
                    .stream()
                    .map(movie -> new MovieGridDTO(movie))
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(movieGridDTOs, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieApi/UpdateMovie", method = RequestMethod.POST)
    public ResponseEntity<MovieDTO> updateMovie(@RequestBody String selectedMovieJSON){

        try {
            Movie selectedMovie = mediaDataService.readMovieDataFromJSON(selectedMovieJSON);
            MovieDTO selectedFixedMovie = movieService.updateSelectedMovie(selectedMovie);
            return new ResponseEntity<>(selectedFixedMovie, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MovieDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieApi/UpdateGridMovie", method = RequestMethod.POST)
    public ResponseEntity<MovieGridDTO> updateGridMovie(@RequestBody String selectedMovieJSON){

        try {
            Movie selectedMovie = mediaDataService.readMovieGridDataFromJSON(selectedMovieJSON);
            MovieGridDTO selectedFixedMovie = movieService.updateSelectedMovieFromGrid(selectedMovie);
            return new ResponseEntity<>(selectedFixedMovie, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new MovieGridDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/MovieApi/DeleteMovie", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteMovie(@RequestParam int MediaID){

        try {
            int DeletedMovieID = movieService.deleteSelectedMovie(MediaID);
            return new ResponseEntity<>(DeletedMovieID, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
