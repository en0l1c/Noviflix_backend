package com.enolic.Noviflix_backend.controller;

import com.enolic.Noviflix_backend.model.dto.MovieDTO;
import com.enolic.Noviflix_backend.service.interfaces.MovieServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MovieController implements MovieServiceInterface {

    private final MovieServiceInterface movieService;

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    @Override
    public MovieDTO getMovieById(UUID id) {
        return movieService.getMovieById(id);
    }

    @Override
    public MovieDTO addMovie(MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @Override
    public MovieDTO updateMovie(UUID id, MovieDTO updatedMovie) {
        return movieService.updateMovie(id, updatedMovie);
    }

    @Override
    public String deleteMovie(UUID id) {
        return movieService.deleteMovie(id);
    }

    @Override
    public MovieDTO getRandomMovie() {
        return movieService.getRandomMovie();
    }

    @Override
    public String loadMovies() {
        return movieService.loadMovies();
    }
}
