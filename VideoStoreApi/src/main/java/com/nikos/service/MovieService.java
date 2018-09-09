package com.nikos.service;

import com.nikos.model.Movie;
import com.nikos.model.dto.MovieDTO;
import com.nikos.model.dto.MovieGridDTO;
import com.nikos.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public ArrayList<Movie> getMovies(){

        try {
            List<Movie> movies = movieRepository.getMediaData("", "");
            return new ArrayList<>(movies);
        }
        catch (Exception ex){
            return null;
        }
    }

    public Movie getMovieByID(int MovieID){

        try{
            Movie selectedMovie = movieRepository.findMediaByMediaID(MovieID);
            return selectedMovie;
        }
        catch (Exception ex){
            return null;
        }
    }

    public MovieGridDTO updateSelectedMovieFromGrid(Movie movie){

        try {
            Movie selectedMovie;
            if (movie.getMediaID() != 0){
                selectedMovie = movieRepository.updateMediaData(movie);
            }
            else {
                selectedMovie = movieRepository.insertNewMediaData(movie);
            }

            MovieGridDTO movieGridDTO = new MovieGridDTO(selectedMovie);
            return movieGridDTO;
        }
        catch (Exception ex){
            return null;
        }
    }

    public MovieDTO updateSelectedMovie(Movie movie){

        try {
            Movie selectedMovie;
            if (movie.getMediaID() != 0){
                selectedMovie = movieRepository.updateMediaData(movie);
            }
            else {
                selectedMovie = movieRepository.insertNewMediaData(movie);
            }

            MovieDTO movieDTO = new MovieDTO(selectedMovie);
            return movieDTO;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<Movie> updateSelectedMovies(List<Movie> movieList){

        try{
            ArrayList<Movie> newUpdatedMovies = new ArrayList<>();
            Movie updatedMovie;

            for (Movie selectedMovie: movieList){

                if (selectedMovie.getMediaID() != 0){
                    updatedMovie = movieRepository.updateMediaData(selectedMovie);
                }
                else {
                    updatedMovie = movieRepository.insertNewMediaData(selectedMovie);
                }

                newUpdatedMovies.add(updatedMovie);
            }

            return newUpdatedMovies;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedMovie(int MovieID){
        try {
            return movieRepository.deleteMedia(MovieID);
        }
        catch(Exception ex){
            return 0;
        }
    }
}
